package model;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.*;

public class NovoCadastroModel extends NovoCadastroController {
    public static void novoCadastro(String nome, String email, String senha) {
        try {
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlPesquisarEmail = "select * from `" + dbPadrao + "`.`" + tblPadrao + "` where `email` = '" + email + "';";
            Statement stmSqlPesquisarEmail = conexao.createStatement();
            ResultSet rstPesquisarEmail = stmSqlPesquisarEmail.executeQuery(strSqlPesquisarEmail);
            if (rstPesquisarEmail.next()) {
                notificarUsuario("Ops! Parece que há um cadastro com esse email. Porfavor, verifique e tente novamente com outro email.");
                stmSqlPesquisarEmail.close();
            } else {
                String campoFoto = "";
                String valorFoto = "";
                if (NovoCadastroController.fileFullName.equals("") == false) {
                    campoFoto = ",`img`";
                    valorFoto = ",'" + fileFullName + "'";
                }
                String strSqlCadastrarRegistro = "insert into `" + dbPadrao + "`.`" + tblPadrao + "` (`nome`,`email`,`senha`" + campoFoto + ") values ('" + nome + "','" + email + "','" + senha +"'" + valorFoto + ");";
                Statement stmSqlCadastrarRegistro = conexao.createStatement();
                stmSqlCadastrarRegistro.addBatch(strSqlCadastrarRegistro);
                stmSqlCadastrarRegistro.executeBatch();
                stmSqlCadastrarRegistro.close();
                notificarUsuario("Cadastro realizado com sucesso!");
                NovoCadastroController.fileFullName = "";
                NovoCadastroController.fileFullPath = "";
                NovoCadastroController.limparCampos();

                if (NovoCadastroController.propriaTela == false) {
                    exibirMenu();
                    // appMenuFrame.setVisible(true);
                    fecharNovoCadastroView();
                    // dispose(); // fechar a própria tela, sem fechar a tela anterior
                }
            }
        } catch (Exception e) {
            notificarUsuario("Não foi possível realizar o cadastro. Por favor, verifique e tente novamente.");
            System.out.println("Erro: " + e);
        }
    }

    public static void copyFile() {
        try {
            JFileChooser chooser = new JFileChooser();
 
            chooser.setDialogTitle("Selecione o arquivo que deseja copiar");
            chooser.setApproveButtonText("Copiar arquivo");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files only", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);
            int returnVal1 = chooser.showOpenDialog(null);
            String fileFullPath = "";
            String fileName = "";
            String selectedFileName = "";
            if(returnVal1 == JFileChooser.APPROVE_OPTION) {
                fileFullPath = chooser.getSelectedFile().getAbsolutePath(); // origem

                selectedFileName = chooser.getSelectedFile().getName();

                String[] fileNameAndExtension = selectedFileName.split("[.]"); // vamos supor que o arquivo tenha o nome: "imagem-do.perfil-do.linkedin.jpg", a matriz de string fileNameAndExtension vai receber os vetores: {"imagem-do", "perfil-do", "linkedin", "jpg"}

                String fileExtension = fileNameAndExtension[fileNameAndExtension.length - 1];
                fileName = "file_" + Math.random() + "." + fileExtension;
                // exemplo: "file_0.2138435168435.jpg"
            }

            String folderFullPath = System.getProperty("user.dir") + "\\src\\view\\img";

            Path pathOrigin = Paths.get(fileFullPath);
            String fileFullPathDestination = folderFullPath + "\\" + fileName;
            Path pathDestination = Paths.get(fileFullPathDestination);
            if (fileFullPath.length() > 0 && folderFullPath.length() > 0) {
                Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING);
                System.out.println("Arquivo " + chooser.getSelectedFile().getName() + " copiado/colado com sucesso.");
                NovoCadastroController.memorizarArquivo(fileFullPathDestination, fileName);
                NovoCadastroController.mostrarFoto();

                NovoCadastroController.notificarUsuario("Imagem foi cadastrada com sucesso.");
            } else {
                NovoCadastroController.notificarUsuario("Ops! Não foi possível selecionar o arquivo. Por favor tente novamente mais tarde.");
            }
        } catch (Exception e) {
            NovoCadastroController.notificarUsuario("Ops! Houve algum problema com a seleção do arquivo. Por favor tente novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }

    public static void removerArquivoModel(String fullPath) {
        try {
            Path pathDestination = Paths.get(fullPath);
            Files.delete(pathDestination);
            JOptionPane.showMessageDialog(null, "Arquivo carregado, não utilizado, removido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops! Houve um problema ao remover o arquivo carregado não utilizado. Por favor, continue com a saída do sistema.");
            System.err.println("Erro: " + e);
        }
    }
}

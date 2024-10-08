package controller;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import java.sql.*;

import javax.swing.*;
import model.*;
import view.*;

public class NovoCadastroController extends NovoCadastroView {
    public static final String dbPadrao = "db_teste";
    public static final String tblPadrao = "tbl_teste";

    public static void notificarUsuario(String textoNotificacao) {
        notificacaoJLabel.setText(textoNotificacao);
    }

    public static void novoCadastroController() {
        NovoCadastroModel.novoCadastro(nomeJTextField.getText(), emailJTextField.getText(), String.valueOf(senhaJPasswordField.getPassword()));
    }

    public static void exibirMenu() {
        MenuFrameView.appMenuFrame.setVisible(true);
    }

    public static void fecharNovoCadastroView() {
        MenuFrameView.appMenuFrame.dispose();
    }

    public class CopyFile {
        public void copyFile() {
            try {
                JFileChooser chooser = new JFileChooser();

                chooser.setDialogTitle("Selecione o arquivo que deseja copiar");
                chooser.setApproveButtonText("Copiar arquivo");
                int returnVal1 = chooser.showOpenDialog(null);
                String fileFullPath = "";
                String fileName = "";
                if(returnVal1 == JFileChooser.APPROVE_OPTION) {
                    fileFullPath = chooser.getSelectedFile().getAbsolutePath(); // origem

                    String[] fileNameAndExtension = chooser.getSelectedFile().getName().split("."); // vamos supor que o arquivo tenha o nome: "imagem-do.perfil-do.linkedin.jpg", a matriz de string fileNameAndExtension vai receber os vetores: {"imagem-do", "perfil-do", "linkedin", "jpg"}

                    String fileExtension = fileNameAndExtension[fileNameAndExtension.length - 1];
                    fileName = "file_" + Math.random() + "." + fileExtension;
                    // exemplo: "file_0.2138435168435.jpg"
                }

                // chooser.setDialogTitle("Selecione a pasta de destino.");
                // chooser.setApproveButtonText("Colar aqui");
                // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                // int returnVal2 = chooser.showOpenDialog(null);
                String folderFullPath = "img";
                // if(returnVal2 == JFileChooser.APPROVE_OPTION) {
                //     folderFullPath = chooser.getSelectedFile().getAbsolutePath();
                // } else {
                //     System.out.println("Que pena! Ok, saindo...");
                //     System.exit(0);
                // }

                Path pathOrigin = Paths.get(fileFullPath);
                Path pathDestination = Paths.get(folderFullPath + "\\" + fileName);
                if (fileFullPath.length() > 0 && folderFullPath.length() > 0) {
                    Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING);
                    System.out.println("Arquivo " + chooser.getSelectedFile().getName() + " copiado/colado com sucesso.");
                    // a partir daqui a gente tem que dar continuidade em adicionar o nome do arquivo no banco de dados

                    Connection conexao = MySQLConnectorModel.conectar();
                    String strSqlCadastrarImagem = "insert into `" + dbPadrao + "`.`" + tblPadrao + "` (`img`) values ('" + fileName + "');";
                    Statement stmSqlCadastrarImagem = conexao.createStatement();
                    stmSqlCadastrarImagem.addBatch(strSqlCadastrarImagem);
                    stmSqlCadastrarImagem.executeBatch();
                    notificacaoJLabel.setText("Imagem foi cadastrada com sucesso.");
                } else {
                    System.out.println("Ops! Não foi possível copiar o arquivo. Por favor, verifique e tente novamente mais tarde.");
                }
            } catch (Exception e) {
                System.out.println("Não foi possível copiar o arquivo.");
            }
        }

        // public static void main(String[] args) throws Exception {
        //     copyFile();
        // }
    }
}

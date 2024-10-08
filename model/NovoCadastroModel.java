package model;
import java.sql.*;

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
                String strSqlCadastrarRegistro = "insert into `" + dbPadrao + "`.`" + tblPadrao + "` (`nome`,`email`,`senha`) values ('" + nome + "','" + email + "','" + senha +"');";
                Statement stmSqlCadastrarRegistro = conexao.createStatement();
                stmSqlCadastrarRegistro.addBatch(strSqlCadastrarRegistro);
                stmSqlCadastrarRegistro.executeBatch();
                stmSqlCadastrarRegistro.close();
                notificarUsuario("Cadastro realizado com sucesso!");
                exibirMenu();
                // appMenuFrame.setVisible(true);
                fecharNovoCadastroView();
                // dispose(); // fechar a própria tela, sem fechar a tela anterior
            }
        } catch (Exception e) {
            notificarUsuario("Não foi possível realizar o cadastro. Por favor, verifique e tente novamente.");
            System.out.println("Erro: " + e);
        }
    }
}

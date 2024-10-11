package model;
import controller.*;
import java.sql.*;
import java.util.*;

public class AtualizarModel {
    public static void popularIdsModel() {
        try {
            ArrayList<String> idsTemp = new ArrayList<>();
            idsTemp.add("Selecione aqui o id");
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlPopularIds = "select * from `db_senac`.`tbl_senac` order by `id` asc;";
            Statement stmSqlPopularIds = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlPopularIds = stmSqlPopularIds.executeQuery(strSqlPopularIds);
            while (rstSqlPopularIds.next()) {
                idsTemp.add(rstSqlPopularIds.getString("id"));
            }
            AtualizarController.enviarIds(idsTemp.toArray(new String[0]));
            stmSqlPopularIds.close();
        } catch (Exception e) {
            AtualizarController.notificarUsuario("Não foi possível encontrar os ids! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }

    public static void atualizarCadastroModel(String atualizarId, String atualizarNome, String atualizarEmail, String atualizarSenha) {
        try {
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlAtualizarId = "update `db_senac`.`tbl_senac` set " + atualizarNome + atualizarEmail + atualizarSenha + " where `id` = " + atualizarId + ";";
            // System.out.println(strSqlAtualizarId);
            Statement stmSqlAtualizarId = conexao.createStatement();
            stmSqlAtualizarId.addBatch(strSqlAtualizarId);
            stmSqlAtualizarId.executeBatch();
            AtualizarController.registrarAtualizacao();
            stmSqlAtualizarId.close();
            AtualizarController.notificarUsuario("O id " + atualizarId + " foi atualizado com sucesso!");
            // lblNotificacoes.setText("O id " + cbxId.getSelectedItem().toString() + " foi atualizado com sucesso!");
        } catch (Exception e) {
            AtualizarController.notificarUsuario("Não foi possível realizar a atualização! Por favor, tente novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }

    public static void atualizarCamposModel(String id) {
        try {
                Connection conexao = MySQLConnectorModel.conectar();
                String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where `id` = " + id + ";";
                Statement stmSqlAtualizarCampos = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);
                if (rstSqlAtualizarCampos.next()) {
                    AtualizarController.enviarCampos(rstSqlAtualizarCampos.getString("nome"), rstSqlAtualizarCampos.getString("email"), rstSqlAtualizarCampos.getString("senha"));
                    AtualizarController.notificarUsuario("Campos atualizados com sucesso!");
                } else {
                    AtualizarController.notificarUsuario("Ops! Não foi encontrado o id selecionado. Por favor, verifique e tente novamente.");
                }
                stmSqlAtualizarCampos.close();
        } catch (Exception e) {
            AtualizarController.notificarUsuario("Não foi possível encontrar os ids! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }
}

package model;
import java.sql.*;
import java.util.*;
import controller.*;

public class RemoverModel {
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
            RemoverController.enviarIds(idsTemp.toArray(new String[0]));
            stmSqlPopularIds.close();
        } catch (Exception e) {
            RemoverController.notificarUsuario("Não foi possível encontrar os ids! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }

    public static void removerIdModel(String id) {
        try {
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlRemoverId = "delete from `db_senac`.`tbl_senac` where `id` = " + id + ";";
            Statement stmSqlRemoverId = conexao.createStatement();
            stmSqlRemoverId.addBatch(strSqlRemoverId);
            stmSqlRemoverId.executeBatch();
            stmSqlRemoverId.close();
            RemoverController.notificarUsuario("O id " + id + " foi removido com sucesso!");
            RemoverController.limparCampos();
            RemoverController.popularIds();
        } catch (Exception e) {
            RemoverController.notificarUsuario("Não foi possível remover o id! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }

    public static void atualizarCamposModel(String id, boolean notificar) {
        try {
                Connection conexao = MySQLConnectorModel.conectar();
                String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where `id` = " + id + ";";
                Statement stmSqlAtualizarCampos = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);
                if (rstSqlAtualizarCampos.next()) {
                    RemoverController.preencherCampos(rstSqlAtualizarCampos.getString("nome"), rstSqlAtualizarCampos.getString("email"));
                    if (notificar == true) {
                        RemoverController.notificarUsuario("Campos atualizados com sucesso!");
                    }
                } else {
                    if (notificar == true) {
                        RemoverController.notificarUsuario("Ops! Não foi encontrado o id selecionado. Por favor, verifique e tente novamente.");
                    }
                }
                stmSqlAtualizarCampos.close();
        } catch (Exception e) {
            RemoverController.notificarUsuario("Não foi possível encontrar os ids! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }
}

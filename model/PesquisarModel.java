package model;
import java.sql.*;

import controller.*;

public class PesquisarModel {
    public static void pesquisarModel(String textoPesquisa) {
        try {
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlPesquisa = "select * from `db_teste`.`tbl_teste` where `nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%' order by `id` asc;";
            Statement stmSqlPesquisa = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlPesquisa = stmSqlPesquisa.executeQuery(strSqlPesquisa);
            if (rstSqlPesquisa.next()) {
                rstSqlPesquisa.last();
                int rowNumbers = rstSqlPesquisa.getRow();
                rstSqlPesquisa.first();

                PesquisarController.notificarUsuario("Legal! Foi(Foram) encontrado(s) " + rowNumbers + " resultado(s).");

                PesquisarController.preencherCampos(rstSqlPesquisa.getString("id"), rstSqlPesquisa.getString("nome"), rstSqlPesquisa.getString("email"));
                PesquisarController.registrarPesquisa();

                PesquisarController.desabilitarPesquisar();
                if (rowNumbers > 1) {
                    PesquisarController.habilitarAvancar();
                }
                stmSqlPesquisa.close();
            } else {
                PesquisarController.registrarPesquisa();
                PesquisarController.desabilitarPesquisar();
                PesquisarController.notificarUsuario("Poxa vida! Não foram encontrados resultados para: \"" + textoPesquisa + "\".");
                stmSqlPesquisa.close();
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            PesquisarController.notificarUsuario("Não foi possível prosseguir com a pesquisa! Por favor, verifique e tente novamente.");
        }
    }

    public static void primeiroRegistroModel(String textoPesquisa) {
        try {
            PesquisarController.limparCamposController("Você está no primeiro registro.");
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlPesquisa = "select * from `db_teste`.`tbl_teste` where `nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%' order by `id` asc;";
            Statement stmSqlPesquisa = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlPesquisa = stmSqlPesquisa.executeQuery(strSqlPesquisa);
            if (rstSqlPesquisa.next()) {
                PesquisarController.preencherCampos(rstSqlPesquisa.getString("id"), rstSqlPesquisa.getString("nome"), rstSqlPesquisa.getString("email"));

                PesquisarController.habilitarAvancar();
            } else {
                PesquisarController.notificarUsuario("Poxa vida! Não foram encontrados resultados para: \"" + textoPesquisa + "\".");
            }
            PesquisarController.registrarPesquisa();

            PesquisarController.desabilitarPesquisar();
            stmSqlPesquisa.close();
        } catch (Exception e) {
            PesquisarController.notificarUsuario("Não foi possível prosseguir com a pesquisa! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }

    public static void registroAnteriorModel(String textoPesquisa, String idAtual, String nomeAtual, String emailAtual) {
        try {
            PesquisarController.limparCamposController("Registro anterior posicionado com sucesso.");
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlProximoRegistro = "select * from `db_teste`.`tbl_teste` where (`nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%') and `id` < " + idAtual + " order by `id` desc;";
            Statement stmSqlProximoRegistro = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);
            if (rstSqlProximoRegistro.next()) {
                PesquisarController.preencherCampos(rstSqlProximoRegistro.getString("id"), rstSqlProximoRegistro.getString("nome"), rstSqlProximoRegistro.getString("email"));
                PesquisarController.habilitarTodos();
            } else {
                PesquisarController.preencherCampos(idAtual, nomeAtual, emailAtual);

                PesquisarController.habilitarAvancar();

                PesquisarController.notificarUsuario("Você chegou ao primeiro registro.");
            }
            stmSqlProximoRegistro.close();
        } catch (Exception e) {
            PesquisarController.notificarUsuario("Não foi possível encontrar o próximo registro! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }

    public static void proximoRegistroModel(String textoPesquisa, String idAtual, String nomeAtual, String emailAtual) {
        try {
            PesquisarController.limparCamposController("Próximo registro posicionado com sucesso.");
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlProximoRegistro = "select * from `db_teste`.`tbl_teste` where (`nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%') and `id` > " + idAtual + " order by `id` asc;";
            Statement stmSqlProximoRegistro = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);
            if (rstSqlProximoRegistro.next()) {
                PesquisarController.preencherCampos(rstSqlProximoRegistro.getString("id"), rstSqlProximoRegistro.getString("nome"), rstSqlProximoRegistro.getString("email"));

                PesquisarController.habilitarTodos();
            } else {
                PesquisarController.preencherCampos(idAtual, nomeAtual, emailAtual);

                PesquisarController.habilitarVoltar();

                PesquisarController.notificarUsuario("Você chegou ao último registro.");
            }
            stmSqlProximoRegistro.close();
        } catch (Exception e) {
            PesquisarController.notificarUsuario("Não foi possível encontrar o próximo registro! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }

    public static void ultimoRegistroModel(String textoPesquisa, String idAtual, String nomeAtual, String emailAtual) {
        try {
            PesquisarController.limparCamposController("");
            Connection conexao = MySQLConnectorModel.conectar();
            String strSqlProximoRegistro = "select * from `db_teste`.`tbl_teste` where `nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%' order by `id` desc;";
            Statement stmSqlProximoRegistro = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);
            if (rstSqlProximoRegistro.next()) {
                PesquisarController.preencherCampos(rstSqlProximoRegistro.getString("id"), rstSqlProximoRegistro.getString("nome"), rstSqlProximoRegistro.getString("email"));

                PesquisarController.habilitarVoltar();

                PesquisarController.notificarUsuario("Você chegou ao último registro.");
            } else {
                PesquisarController.preencherCampos(idAtual, nomeAtual, emailAtual);

                PesquisarController.habilitarVoltar();

                PesquisarController.notificarUsuario("Você chegou ao último registro.");
            }
            stmSqlProximoRegistro.close();
        } catch (Exception e) {
            PesquisarController.notificarUsuario("Não foi possível encontrar o último registro! Por favor, verifique e tente novamente.");
            System.err.println("Erro: " + e);
        }
    }
}
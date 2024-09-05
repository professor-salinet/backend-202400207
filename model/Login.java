package model;
import controller.*;

import java.awt.*;
import java.sql.*;

public class Login {
    public static boolean loginUsuario(String login, String senha) {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlLogin = "select * from `" + Interface.dbPadrao + "`.`" + Interface.tblPadrao + "`" + " where `email` = '" + login + "' and `senha` = '" + senha + "';";
            System.out.println(strSqlLogin);
            Statement stmSqlLogin = conexao.createStatement();
            ResultSet rstSqlLogin = stmSqlLogin.executeQuery(strSqlLogin);
            if (rstSqlLogin.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
}

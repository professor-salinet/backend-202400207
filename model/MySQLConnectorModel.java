package model;
import java.sql.*;

public class MySQLConnectorModel {
    public static Connection conectar() {
        String mysqlHost = "127.0.0.1";
        String mysqlDb = "db_mysql_connector";
        String mysqlUser = "root";
        String mysqlPassword = "senac@02";
        String mysqlPort = "3306";
        String mysqlUrl = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + mysqlDb + "?user=" + mysqlUser + "&password=" + mysqlPassword;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(mysqlUrl);
            System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
            System.err.println("Ops! Algo de errado não está certo com a conexão com o banco de dados MySQL! Mensagem do servidor: " + e);
        }
        return conn;
    }
}

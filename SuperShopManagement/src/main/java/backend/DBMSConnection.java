package backend;

import java.sql.*;
public class DBMSConnection {
    String dbUrl;
    String userName;
    String password;

    public DBMSConnection(String dbUrl, String userName, String password) {
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
    Connection con=null;
    Class.forName("com.mysql.cj.jdbc.Driver");
    con=DriverManager.getConnection(dbUrl,userName,password);
        System.out.println("Connection estublished");
        return con;
    }

    public void closeConnection(Connection con,Statement stmt) throws SQLException {
        stmt.close();
        con.close();
        System.out.println("Connection closed");

    }

}

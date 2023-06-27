package br.ifsp.dsw3.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connections implements AutoCloseable{
    private static PreparedStatement stmt = null;
    private static Connection conn = null;

    public static Connection createConnection() {
        try {
            if (conn == null){
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:teste.db");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement createStatement(String sql) {
        try {
            stmt = createConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    @Override
    public void close() throws Exception {
        if (conn != null)
            conn.close();
        if (stmt != null)
            stmt.close();
    }    
}
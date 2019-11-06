/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de conexão com o banco de dados
 * @author thiago
 */
public class ConnectionDAO {
    private static final String db = "jdbc:mysql://localhost:3306/tasklist";
    private static final String user = "root";
    private static final String password = "123456";
    
    /**
     * Retorna uma conexão do banco
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    db + "?verifyServerCertificate=false"
                            + "&requireSSL=false"
                            + "&useSSL=false"
                            + "&serverTimeZone=UTC",
                    user,
                    password
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Fecha uma conexão do banco
     * @param connection 
     */
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Fecha uma conexão do banco e uma PreparedStatement
     * @param connection
     * @param pstm 
     */
    public static void closeConnection(Connection connection, PreparedStatement pstm) {
        try {
            pstm.close();
            ConnectionDAO.closeConnection(connection);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Fecha uma conexão do banco uma PreparedStatement
     * e fecha também um ResultSet
     * @param connection
     * @param pstm
     * @param rs 
     */
    public static void closeConnection(Connection connection, PreparedStatement pstm, ResultSet rs) {
        try {
            rs.close();
            ConnectionDAO.closeConnection(connection, pstm);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
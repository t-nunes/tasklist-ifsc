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
 *
 * @author thiago
 */
public class Conexao {
    private static final String banco = "jdbc:mysql://localhost:3306/tasklist";
    private static final String usuario = "root";
    private static final String senha = "123456";
    
    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(
                    banco + "?verifyServerCertificate=false"
                            + "&requireSSL=false"
                            + "&useSSL=false"
                            + "&serverTimeZone=UTC",
                    usuario,
                    senha
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void closeConnection(Connection conexao) {
        try {
            conexao.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement pstm) {
        try {
            pstm.close();
            Conexao.closeConnection(conexao);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement pstm, ResultSet rs) {
        try {
            rs.close();
            Conexao.closeConnection(conexao, pstm);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
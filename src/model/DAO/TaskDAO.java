/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import transformers.TaskTransformer;

/**
 *
 * @author thiago
 */
public class TaskDAO {
    /**
     * Retorna uma lista de tarefas
     */
    public List<Task> fetchAll() {
        Connection connection = ConnectionDAO.getConnection();
        
        String sql = "select * from task where active = true";
        
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        
        try {
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            
            // Cria uma lista de tarefas
            List<Task> tasks = new ArrayList<>();
            
            while(resultSet.next()) {
                tasks.add(TaskTransformer.transform(resultSet));
            }
            
            // Fecha a conexão
            ConnectionDAO.closeConnection(connection, pstm, resultSet);

            return tasks;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Busca uma tarefa pelo id
     * @param id
     * @return 
     */
    public Task fetchOne(int id) {
        Connection connection = ConnectionDAO.getConnection();
        
        String sql = "select * from editora where id = ?";
        
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            
            // vai para o próximo registro
            resultSet.next();
            
            Task task = TaskTransformer.transform(resultSet);
            
            ConnectionDAO.closeConnection(connection, pstm, resultSet);

            return task;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Insere uma tarefa
     * @param task 
     */
    public void insert(Task task) {
        Connection connection = ConnectionDAO.getConnection();
        
        String sql = "insert into task (description) values (?)";
        
        PreparedStatement pstm = null;
        
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, task.getDescription());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        ConnectionDAO.closeConnection(connection, pstm);
    }
    
    /**
     * Atualiza uma tarefa
     * @param task
     */
    public void update(Task task) {
        Connection connection = ConnectionDAO.getConnection();
        
        String sql = "update task set description = ? where id = ?";
        
        PreparedStatement pstm = null;
        
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, task.getDescription());
            pstm.setInt(2, task.getId());
            pstm.executeUpdate();
            
            ConnectionDAO.closeConnection(connection, pstm);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Deleta uma tarefa (soft delete)
     * @param id 
     */
    public void delete(int id) {
        Connection connection = ConnectionDAO.getConnection();
        
        String sql = "update task set status = false where id = ?";
        
        PreparedStatement pstm = null;
        
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            ConnectionDAO.closeConnection(connection, pstm);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Da play em uma tarefa
     * @param id 
     */
    public void play(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Para a tarefa
     * @param id 
     */
    public void stop(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

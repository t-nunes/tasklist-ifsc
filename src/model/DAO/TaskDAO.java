/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Task;

/**
 *
 * @author thiago
 */
public class TaskDAO {
    public void Insert(Task task) {
        Connection conexao = Conexao.getConexao();
    }

    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Task task, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insert(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Task> fetchAll(Task task) {
        List<Task> lista = new ArrayList<>();
        return lista;
    }

    public Task fetchOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return new Task("1", "");
    }
}

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
    /**
     * Deleta uma tarefa (soft delete)
     * @param id 
     */
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Atualiza uma tarefa
     * @param task
     * @param id 
     */
    public void update(Task task, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Insere uma tarefa
     * @param task 
     */
    public void insert(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Busca uma lista de tarefas
     * @return 
     */
    public List<Task> fetchAll() {
        Task task = new Task(
                1,
                "1",
                0,
                "2019-05-18 20:20:20",
                "2019-05-18 20:20:20",
                "2019-05-18 10:30:00",
                false
        );
        List<Task> lista = new ArrayList<>();
        lista.add(task);
        return lista;
    }

    /**
     * Busca uma tarefa pelo id
     * @param id
     * @return 
     */
    public Task fetchOne(int id) {
        Task task = new Task(
                1,
                "1",
                0,
                "2019-05-18 20:20:20",
                "2019-05-18 20:20:20",
                "2019-05-18 10:30:00",
                false
        );
        
        return task;
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

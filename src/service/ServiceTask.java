/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.DAO.TaskDAO;
import model.Task;

/**
 *
 * @author thiago
 */
public class ServiceTask {
    public static void create(Task task) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.insert(task);
    }
    
    public static void delete(int id) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.delete(id);
    }
    
    public static void update(Task task, int id) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.update(task, id);
    }
    
    /**
     * 
     * @param task 
     */
    public static List<Task> fetchAll(Task task) {
        TaskDAO taskDAO = new TaskDAO();
        return taskDAO.fetchAll(task);
    }
    
    /**
     * Busca uma tarefa
     * @param {int} id 
     */
    public static void fetchOne(int id) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.fetchOne(id);
    }
}

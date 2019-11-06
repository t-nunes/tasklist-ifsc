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
    /**
     * Cria uma nova tarefa
     * @param task 
     */
    public static void create(Task task) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.insert(task);
    }
    
    /**
     * Deleta uma tarefa
     * @param id 
     */
    public static void delete(int id) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.delete(id);
    }
    
    /**
     * Atualiza uma tarefa
     * @param task
     */
    public static void update(Task task) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.update(task);
    }
    
    /**
     * Retorna a lista de tarefas
     * @param task 
     */
    public static List<Task> fetchAll() {
        TaskDAO taskDAO = new TaskDAO();
        return taskDAO.fetchAll();
    }
    
    /**
     * Busca uma tarefa
     * @param {int} id 
     */
    public static void fetchOne(int id) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.fetchOne(id);
    }
    
    
    public static void stop(int id) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.stop(id);
    }
}

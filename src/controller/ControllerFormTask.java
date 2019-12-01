/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Task;
import service.ServiceTask;
import view.FormTask;

/**
 *
 * @author thiago
 */
public class ControllerFormTask implements ActionListener {
    private FormTask formTask;
    private ControllerTaskMain controllerMain;
    private Task task = null;

    public ControllerFormTask(
            FormTask formTask, 
            ControllerTaskMain controllerMain
    ) {
        this.formTask = formTask;
        this.controllerMain = controllerMain;
        
        this.formTask.getButtonSubmit().addActionListener(this);
        this.formTask.getButtonDismiss().addActionListener(this);
    }
    
    public ControllerFormTask(
            FormTask formTask, 
            ControllerTaskMain controllerMain,
            Task task
    ) {
        this(formTask, controllerMain);
        
        this.task = task;
        this.setTask(task);
    }
    
    /**
     * Retorna a descrição da tarefa
     * @return 
     */
    public String getDescription() {
        return this.formTask.getDescription();
    }
    
    public void setDescription(String description) {
        this.formTask.getInputDescription().setText(description);
    }
    
    public void setTask(Task task) {
        this.setDescription(task.getDescription());
    }
    
    public Task getTask() {
        String description = this.getDescription();
        
        if (this.task != null) {
            this.task.setDescription(description);
            return this.task;
        }
        
        Task task = new Task();
        task.setDescription(description);
        
        return task;
    }
    
    public void handleSubmit() {
        
        if (this.task == null) {
            ServiceTask.create(this.getTask());
        } else {
            ServiceTask.update(this.getTask());
        }
        
        this.formTask.dispose();
        
        // Atualiza a listagem
        controllerMain.updateTable();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == this.formTask.getButtonSubmit()) {
            this.handleSubmit();
        } else if (source == this.formTask.getButtonDismiss()) {
            this.formTask.dispose();
        }
    }
    
}

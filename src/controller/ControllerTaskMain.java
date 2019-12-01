/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helpers.DateHelpers;
import helpers.StringHelpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Task;
import service.ServiceTask;
import view.FormTask;
import view.JFMain;

/**
 *
 * @author thiago
 */
public class ControllerTaskMain implements ActionListener, ListSelectionListener {
    private JFMain mainView;
    protected FormTask formTask;
    private Timer timer;
    private TimerTask timerTask;
    private int wastedSeconds;
    private DefaultTableModel model;
    
    public ControllerTaskMain(JFMain mainView) {
        // Armazena a referencia da tela
        this.mainView = mainView;
        
        // Adiciona eventos nos elementos
        this.mainView.getButtonTaskAdd().addActionListener(this);
        this.mainView.getMenuClose().addActionListener(this);
        this.mainView.getButtonTaskUpdate().addActionListener(this);
        this.mainView.getButtonTaskDelete().addActionListener(this);
        this.mainView.getButtonTaskToggleStatus().addActionListener(this);
        this.mainView.getTableTasks().getSelectionModel().addListSelectionListener(this);

        // atualiza a tabela quando entra
        this.updateTable();
    }
    
    /**
     * Retorna o id que está selecionado na tabela
     * @return 
     */
    public int getTaskId() {
        JTable table = this.mainView.getTableTasks();
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
    }
    
    /**
     * Pega a tarefa selecionada
     * @return 
     */
    public Task getSelectedTask() {
        return ServiceTask.fetchOne(this.getTaskId());
    }
    
    /**
     * Atualiza a lista de tarefas
     */
    public void updateTable() {
        this.clearTimers();
        
        // Pega a tabela das tarefas
        JTable table = this.mainView.getTableTasks();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        model.setRowCount(0);

        for (Task task : ServiceTask.fetchAll()) {
            model.addRow(new Object[]{
                String.valueOf(task.getId()), 
                task.getDescription()
            });
        }
    }
    
    /**
     * Limpa todos os timers que estiverem em execução
     * para envitar uma possível execução duplicada
     */
    public void clearTimers() {
        // Pega o label do timer
        JLabel labelTimer = this.mainView.getLabelTimer();
        labelTimer.setText("--:--:--");
        
        if (this.timer != null) {
            this.timer.cancel();
            this.timer.purge();
        }
    }
    
    /**
     * Atualiza o tempo do label
     * @param task 
     */
    public void updateLabelTime(Task task) {
        // Pega o label do timer
        JLabel labelTimer = this.mainView.getLabelTimer();
        
        if (task.getStartedPlay() != null) {
            long secondsLong = (DateHelpers.diffInSeconds(task.getStartedPlay()) + task.getWastedSeconds()) - 3600;

            int splited[] = DateHelpers.splitToComponentTimes(
                    BigDecimal.valueOf(secondsLong)
            );

            String hours = StringHelpers.padLeftZeros(splited[0], 2);
            String minutes = StringHelpers.padLeftZeros(splited[1], 2);
            String seconds = StringHelpers.padLeftZeros(splited[2], 2);

            String time = hours + ":" + minutes + ":" + seconds;

            // Seta o tempo em segundos no label
            labelTimer.setText(time);
        }
    }
    
    /**
     * Atualiza o timer do tempo decorrido
     * @param task 
     */
    public void updateTimer(Task task) {
        this.clearTimers();
        
        // Atualiza o tempo da tarefa logo de inicio
        // pra não ficar um delay pra atualizar
        this.updateLabelTime(task);
        
        if (task.getStartedPlay() != null) {
            // Pega o label do timer
            JLabel labelTimer = this.mainView.getLabelTimer();

            // Guarda a referencia do this em uma variavel
            // para poder utilizar o metodo da classe dentro de outra
            // {GAMBIARRA}
            ControllerTaskMain _this = this;

            this.timer = new Timer();
            this.timerTask = new TimerTask() {
                @Override
                public void run() {
                    _this.updateLabelTime(task);
                };
            };

            this.timer.schedule(this.timerTask,1000,1000);
        } else {
            long secondsLong = task.getWastedSeconds();

            int splited[] = DateHelpers.splitToComponentTimes(
                    BigDecimal.valueOf(secondsLong)
            );

            String hours = StringHelpers.padLeftZeros(splited[0], 2);
            String minutes = StringHelpers.padLeftZeros(splited[1], 2);
            String seconds = StringHelpers.padLeftZeros(splited[2], 2);

            String time = hours + ":" + minutes + ":" + seconds;

            // Seta o tempo em segundos no label
            this.mainView.getLabelTimer().setText(time);
        }
        
        
    }
    
    /**
     * Atualiza a tarefa selecionada
     * @param task 
     */
    public void updateSelectedTask(Task task) {
        JLabel currentLabel = this.mainView.getLabelCurrentTask();
        currentLabel.setText(task.getDescription());
        this.updateTimer(task);
    }
    
    public void openFormTask(Task task) {
        //Abre janela para consulta dos dados
        FormTask telaConsulta = new FormTask(null, true);
        ControllerFormTask controllerConsultaLivros = new ControllerFormTask(telaConsulta, this, task);
        telaConsulta.setVisible(true);
    }
    
    public void openFormTask() {
        //Abre janela para consulta dos dados
        FormTask telaConsulta = new FormTask(null, true);
        ControllerFormTask controllerConsultaLivros = new ControllerFormTask(telaConsulta, this);
        telaConsulta.setVisible(true);
    }
    
    /**
     * Lida com o clique para abrir a modal de tarefa
     */
    public void handleClickTaskAdd() {
        this.openFormTask();
    }
    
    
    public void handleClickTaskUpdate() {
        Task selectedTask = this.getSelectedTask();
        this.openFormTask(selectedTask);
    }
    
    /**
     * Funcão que lida com a exclusão de uma tarefa
     */
    public void handleClickTaskDelete() {
        Task task = this.getSelectedTask();
        ServiceTask.delete(task.getId());
        this.updateTable();
    }
    
    public void handleClickTaskToggleStatus() {
        Task task = this.getSelectedTask();
        
        if (task.getStartedPlay() != null) {
            ServiceTask.stop(task);
        } else {
            ServiceTask.play(task.getId());
        }
        
        Task updatedTask = ServiceTask.fetchOne(task.getId());
        this.updateSelectedTask(updatedTask);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == this.mainView.getButtonTaskAdd()) {
            this.handleClickTaskAdd();
        } else if (source == this.mainView.getButtonTaskUpdate()) {
            this.handleClickTaskUpdate();
        } else if (source == this.mainView.getMenuClose()) {
            this.mainView.dispose();
            System.exit(0);
        } else if (source == this.mainView.getButtonTaskDelete()) {
            this.handleClickTaskDelete();
        } else if (source == this.mainView.getButtonTaskToggleStatus()) {
            this.handleClickTaskToggleStatus();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable table = this.mainView.getTableTasks();
        
        if (e.getValueIsAdjusting()) {
            String idString = table.getValueAt(table.getSelectedRow(), 0).toString();
            
            // Pega o id da tarefa
            int id = Integer.valueOf(idString);
            
            Task task = this.getSelectedTask();

            if (task != null) {
                this.updateSelectedTask(task);
            }
        }
    }
    
}

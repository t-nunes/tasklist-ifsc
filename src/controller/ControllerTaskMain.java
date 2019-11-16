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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Task;
import service.ServiceTask;
import view.JFMain;

/**
 *
 * @author thiago
 */
public class ControllerTaskMain implements ActionListener, ListSelectionListener {
    private JFMain mainView;
    private Timer timer;
    private TimerTask timerTask;
    private int wastedSeconds;
    
    public ControllerTaskMain(JFMain mainView) {
        this.mainView = mainView;
        
        this.mainView.getButtonTaskAdd().addActionListener(this);
        this.mainView.getButtonTaskDelete().addActionListener(this);
        this.mainView.getTableTasks().getSelectionModel().addListSelectionListener(this);
        
        DefaultTableModel table = (DefaultTableModel) this.mainView.getTableTasks().getModel();

        this.mainView.getTableTasks().removeAll();

        for (Task task : ServiceTask.fetchAll()) {
            table.addRow(new Object[]{
                String.valueOf(task.getId()), 
                task.getDescription()
            });
        }
    }
    
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
            long secondsLong = DateHelpers.diffInSeconds(task.getStartedPlay()) - 3600;

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
    
    public void updateTimer(Task task) {
        this.clearTimers();
        
        // Atualiza o tempo da tarefa logo de inicio
        // pra não ficar um delay pra atualizar
        this.updateLabelTime(task);
        
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
    
    public void handleClickTaskAdd() {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.mainView.getButtonTaskAdd()) {
            this.handleClickTaskAdd();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Task task = this.getSelectedTask();
            this.updateSelectedTask(task);
        }
    }
    
}

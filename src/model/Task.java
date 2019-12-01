/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author thiago
 */
public class Task {
    private int id;
    
    // Descrição da tarefa
    private String description;
    
    // Pega a quantidade de segundos gastos
    private int wastedSeconds;
    
    // Data que a tarefa foi criada/atualizada e iniciada
    private String createdAt, updatedAt, startedPlay;
    
    // Status de visibilidade usado soft-delete
    private boolean active;
    
    public Task() {
        
    }
    
    /**
     * Construtor da classe de tarefa quando está criando uma nova
     * @param description 
     */
    public Task(
            int id,
            String description,
            int wastedSeconds,
            String createdAt,
            String updatedAt,
            String startedPlay,
            boolean active
    ) {
        this.id = id;
        this.description = description;
        this.wastedSeconds = wastedSeconds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startedPlay = startedPlay;
        this.active = active;
    }
    
    /**
     * Retorna se a tarefa está sendo executada
     */
    public boolean isPlaying() {
        return Boolean.parseBoolean(startedPlay);
    }
    
    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getWastedSeconds() { return wastedSeconds; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getStartedPlay() { return startedPlay; }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}

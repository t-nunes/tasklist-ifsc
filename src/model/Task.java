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
    // Descrição da tarefa
    private String description;
    
    // Data que a tarefa foi criada/atualizada e iniciada
    private LocalDateTime createdAt, updatedAt, startedPlay;
    
    // Marca o status da tarefa 
    private boolean status;
    
    /**
     * Construtor da classe de tarefa quando está criando uma nova
     * @param description 
     */
    Task(
            String description,
            String createdAt,
            String updatedAt,
            String startedPlay,
            boolean status
    ) {
        this.description = description;
        this.createdAt = LocalDateTime.parse(createdAt);
        this.updatedAt = LocalDateTime.parse(updatedAt);
        this.startedPlay = LocalDateTime.parse(startedPlay);
        this.status = status;
    }
    
    /**
     * Construtor da classe de tarefa quando está criando uma nova
     * @param description 
     */
    Task(
            String description,
            String createdAt,
            String updatedAt,
            boolean status
    ) {
        this(description, createdAt, updatedAt, null, status);
    }
    
    public boolean isPlaying() {
        return this.startedPlay;
    }
    
    
}

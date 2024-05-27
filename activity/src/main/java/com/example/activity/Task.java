package com.example.activity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;
    private String title;
    private String description;
    private String completed;

    public Task(){

    }

    public Task(Long id, String title, String description, String completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCompleted() {
        return completed;
    }
    public void setCompleted(String completed) {
        this.completed = completed;
    }
}

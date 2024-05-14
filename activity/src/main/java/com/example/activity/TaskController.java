package com.example.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    @Operation(summary = "Busca a lista de todos os task")
    public ResponseEntity<List<Task>> getAllTask(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
    }

    @GetMapping("/task{id}")
    @Operation(summary = "Busca um task a partir do seu identificador")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "id") Long id){
        Optional<Task> taskO = taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskO.get());
    }

    @PutMapping("/task{id}")
    @Operation(summary = "Atualiza um task a partir do seu identificador")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "id") Long id){
        Optional<Task> taskO = taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskO.get());
    }

    @DeleteMapping("/task{id}")
    @Operation(summary = "Deleta um task a apartir do seu identificador")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") Long id){
        Optional<Task> taskO = taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskO.get());
    }
    
}

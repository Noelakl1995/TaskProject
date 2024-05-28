package com.example.activity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.activity.controllers.TaskController;
import com.example.activity.services.TaskService;


@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;


    @InjectMocks
    TaskController taskController;

    @Mock
    TaskService taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }


    @Test
    void shouldCreateTask() throws Exception {
        String requestBody =  "{\"id\": 1, \"title\": \"sistema\", \"description\": \"algoritmo\", \"completed\": \"fim\"}";
        Task mockTask = new Task(1L, "sistema", "algoritmo", "fim");
        when(taskService.save(any(Task.class))).thenReturn(mockTask);

        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("sistema"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("algoritmo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value("fim"));
    }




    @Test
    void shouldReturnTaskBasedOnItsId() throws Exception{
        Task mockTask = new Task(1L, "sistema", "algoritmo", "fim");
        when(taskService.findById(1L)).thenReturn(Optional.of(mockTask));

        mockMvc.perform(MockMvcRequestBuilders.get("/task/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("sistema"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("algoritmo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value("fim"));
    }


    @Test
    void shouldUpdateTask() throws Exception {
        String requestBody = "{\"title\": \"sistema atualizado\", \"description\": \"algoritmo atualizado\", \"completed\": \"fim atualizado\"}";
        Task mockTask = new Task(1L, "sistema", "algoritmo", "fim");
        Task updatedTask = new Task(1L, "sistema atualizado", "algoritmo atualizado", "fim atualizado");
        when(taskService.findById(1L)).thenReturn(Optional.of(mockTask));
        when(taskService.save(any(Task.class))).thenReturn(updatedTask);

        mockMvc.perform(MockMvcRequestBuilders.put("/task/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("sistema atualizado"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("algoritmo atualizado"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value("fim atualizado"));
    }


  @Test
void shouldDeleteTask() throws Exception {
    Task mockTask = new Task(1L, "sistema", "algoritmo", "fim");
    when(taskService.findById(1L)).thenReturn(Optional.of(mockTask));
    doNothing().when(taskService).deleteById(1L); // Simular a exclusão sem retorno

    mockMvc.perform(MockMvcRequestBuilders.delete("/task/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
}



    @Test
    void shouldReturnAllTask() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/task"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

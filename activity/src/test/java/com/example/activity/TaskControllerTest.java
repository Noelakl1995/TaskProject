package com.example.activity;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.activity.services.TaskService;


@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskService taskService;

    @Test
    void shouldCreateTask() throws Exception{
        String requestBody =  "{\"id\": \"1\", \"title\": \"sistema\", \"description\": \"algoritmo\", \"completed\": \"fim\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("sistema"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("algoritmo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value("fim"));
    }


    @Test
    void shouldReturnTaskBasedOnItsId() throws Exception{
        Task task = new Task(1L, "sistema", "algoritmo", "fim");
        when(taskService.findById(1L)).thenReturn(Optional.of(task));

        mockMvc.perform(MockMvcRequestBuilders.get("/task/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("sistema"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("algoritmo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value("fim"));
    }


    @Test
    void shouldReturnAllTask() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/task"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

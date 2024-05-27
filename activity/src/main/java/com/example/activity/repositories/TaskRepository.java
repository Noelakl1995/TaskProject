package com.example.activity.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.activity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}

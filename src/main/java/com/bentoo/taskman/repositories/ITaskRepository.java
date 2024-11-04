package com.bentoo.taskman.repositories;

import com.bentoo.taskman.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITaskRepository extends JpaRepository<Task, UUID> {
    Task findByUserId(UUID userId);
}

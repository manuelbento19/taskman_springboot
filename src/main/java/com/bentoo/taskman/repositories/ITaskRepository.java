package com.bentoo.taskman.repositories;

import com.bentoo.taskman.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByUserId(UUID userId);
}

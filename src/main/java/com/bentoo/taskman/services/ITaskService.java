package com.bentoo.taskman.services;

import com.bentoo.taskman.dto.TaskDTO;
import com.bentoo.taskman.models.Task;

import java.util.List;
import java.util.UUID;

public interface ITaskService {
    public Task Create(TaskDTO data) throws Exception;
    public List<Task> FilterByUser(UUID userId);
    public Task Update(UUID id, UUID userId, TaskDTO data) throws Exception;
}

package com.bentoo.taskman.services.implementations;

import com.bentoo.taskman.dto.TaskDTO;
import com.bentoo.taskman.models.Task;
import com.bentoo.taskman.repositories.ITaskRepository;
import com.bentoo.taskman.services.ITaskService;
import com.bentoo.taskman.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Task Create(TaskDTO data) throws Exception {
        var currentDate = LocalDateTime.now();
        if(data.getStartAt().isBefore(currentDate) || data.getEndAt().isBefore(currentDate))
            throw new Exception("startDate and endDate can't be before currentDate");
        if(data.getEndAt().isBefore(data.getStartAt()))
            throw new Exception("endDate can't be before startDate");

        var task = mapper.map(data, Task.class);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> FilterByUser(UUID userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task Update(UUID id, UUID userId, TaskDTO data) throws Exception {
        var taskExists = taskRepository.findById(id);
        if(taskExists.isEmpty())
            throw new Exception("Task doesn't exists");

        var task = taskExists.get();
        if(!task.getUser().getId().equals(userId))
            throw new Exception("You don't have permission to update this task");

        utils.copyNonNullProperties(data,taskExists.get());
        return taskRepository.save(taskExists.get());
    }
}

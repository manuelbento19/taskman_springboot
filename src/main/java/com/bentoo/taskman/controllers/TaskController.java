package com.bentoo.taskman.controllers;
import com.bentoo.taskman.dto.TaskDTO;
import com.bentoo.taskman.models.Task;
import com.bentoo.taskman.repositories.ITaskRepository;
import com.bentoo.taskman.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    Utils utils;

    @PostMapping
    public ResponseEntity Create(@RequestBody TaskDTO body, ServletRequest request){
        UUID userId = (UUID) request.getAttribute("userId");
        body.setUserId(userId);
        var response = mapper.map(body, Task.class);

        var currentDate = LocalDateTime.now();
        if(response.getStartAt().isBefore(currentDate) || response.getEndAt().isBefore(currentDate))
            return ResponseEntity.badRequest().body("startDate and endDate can't be before currentDate");
        if(response.getEndAt().isBefore(response.getStartAt()))
            return ResponseEntity.badRequest().body("endDate can't be before startDate");

        var result = taskRepository.save(response);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).body(result);
    }

    @GetMapping()
    public List<Task> GetTasks(ServletRequest request){
        UUID userId = (UUID) request.getAttribute("userId");
        return taskRepository.findAllByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity Update(@RequestBody TaskDTO body, @PathVariable UUID id, ServletRequest request){
        var taskExists = taskRepository.findById(id);
        if(taskExists.isEmpty())
            return ResponseEntity.badRequest().body("Task doesn't exists");

        utils.copyNonNullProperties(body,taskExists.get());
        var response = taskRepository.save(taskExists.get());
        return ResponseEntity.ok().body(response);
    }
}

package com.bentoo.taskman.controllers;
import com.bentoo.taskman.dto.TaskDTO;
import com.bentoo.taskman.models.Task;
import com.bentoo.taskman.services.ITaskService;
import com.bentoo.taskman.utils.Utils;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;
    @Autowired
    Utils utils;

    @PostMapping
    public ResponseEntity Create(@RequestBody TaskDTO body, ServletRequest request) throws Exception {
        UUID userId = (UUID) request.getAttribute("userId");
        body.setUserId(userId);
        var result = taskService.Create(body);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).body(result);
    }

    @GetMapping()
    public List<Task> GetTasks(ServletRequest request){
        UUID userId = (UUID) request.getAttribute("userId");
        return taskService.FilterByUser(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity Update(@RequestBody TaskDTO body, @PathVariable UUID id, ServletRequest request) throws Exception {
        UUID userId = (UUID) request.getAttribute("userId");
        var response = taskService.Update(id,userId,body);
        return ResponseEntity.ok().body(response);
    }
}

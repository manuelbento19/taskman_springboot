package com.bentoo.taskman.controllers;
import com.bentoo.taskman.dto.TaskDTO;
import com.bentoo.taskman.models.Task;
import com.bentoo.taskman.repositories.ITaskRepository;
import jakarta.servlet.ServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ITaskRepository taskRepository;
    ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity Create(@RequestBody TaskDTO body, ServletRequest request){
        body.setUserId((UUID) request.getAttribute("userId"));
        var response = mapper.map(body, Task.class);

        var currentDate = LocalDateTime.now();
        if(response.getStartAt().isBefore(currentDate) || response.getEndAt().isBefore(currentDate))
            return ResponseEntity.badRequest().body("startDate and endDate can't be before currentDate");
        if(response.getEndAt().isBefore(response.getStartAt()))
            return ResponseEntity.badRequest().body("endDate can't be before startDate");

        var result = taskRepository.save(response);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).body(result);
    }
}

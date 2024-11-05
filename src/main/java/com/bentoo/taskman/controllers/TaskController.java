package com.bentoo.taskman.controllers;

import com.bentoo.taskman.dto.TaskDTO;
import com.bentoo.taskman.models.Task;
import com.bentoo.taskman.repositories.ITaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ITaskRepository taskRepository;
    ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity Create(@RequestBody TaskDTO body){
        var response = mapper.map(body, Task.class);
        var result = taskRepository.save(response);
        System.out.println(result);
        return  ResponseEntity.ok().body("Created");
    }
}

package com.bentoo.taskman.controllers;

import com.bentoo.taskman.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TaskController{

    @PostMapping
    public ResponseEntity<UserDTO> Create(@RequestBody UserDTO data){
        return ResponseEntity.ok().body(data);
    }
}
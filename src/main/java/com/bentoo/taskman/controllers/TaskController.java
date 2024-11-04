package com.bentoo.taskman.controllers;
import com.bentoo.taskman.dto.UserDTO;
import com.bentoo.taskman.models.User;
import com.bentoo.taskman.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class TaskController{

    @Autowired
    IUserRepository userRepository;

    ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<User> Create(@RequestBody User body){
        var data = mapper.map(body,User.class);
        User result = userRepository.save(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
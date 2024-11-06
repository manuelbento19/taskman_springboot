package com.bentoo.taskman.controllers;
import com.bentoo.taskman.dto.UserDTO;
import com.bentoo.taskman.models.User;
import com.bentoo.taskman.services.IUserService;
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
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity Create(@RequestBody UserDTO body) throws Exception{
        var data = mapper.map(body, User.class);
        User result = userService.Create(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
package com.bentoo.taskman.services.implementations;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.bentoo.taskman.exceptions.AppError;
import com.bentoo.taskman.models.User;
import com.bentoo.taskman.repositories.IUserRepository;
import com.bentoo.taskman.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User Create(User data) {
        var userExists = userRepository.findByEmail(data.getEmail());
        if (userExists != null) {
            throw new AppError("User already exists");
        }
        String hashPassword = BCrypt.withDefaults().hashToString(10,data.getPassword().toCharArray());
        data.setPassword(hashPassword);

        return userRepository.save(data);
    }
}

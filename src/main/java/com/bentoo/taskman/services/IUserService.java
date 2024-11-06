package com.bentoo.taskman.services;

import com.bentoo.taskman.models.User;

public interface IUserService {
    public User Create(User data) throws Exception;
}

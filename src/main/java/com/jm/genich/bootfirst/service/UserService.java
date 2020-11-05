package com.jm.genich.bootfirst.service;

import com.jm.genich.bootfirst.models.User;

import java.util.List;

public interface UserService {
    void regUser(User user);

    boolean existsById (Long id);

    void deleteUser(Long id);

    User getUser(Long id);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUserByLogin(String login);
}

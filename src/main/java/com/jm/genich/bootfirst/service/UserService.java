package com.jm.genich.bootfirst.service;

import com.jm.genich.bootfirst.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    boolean existsById (Long id);

    void deleteUser(Long id);

    User getUser(Long id);

    void updateUser(User user);

    List<User> showUsers();

    User getUserByLogin(String login);
}

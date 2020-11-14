package com.jm.genich.bootfirst.controllers;

import com.jm.genich.bootfirst.exceptions.NotFoundException;
import com.jm.genich.bootfirst.models.DTOUser;
import com.jm.genich.bootfirst.models.User;
import com.jm.genich.bootfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")

public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {

        User user = userService.getUser(id);
        if (user == null) {
            throw new NotFoundException();
        } else return user;
    }

    @PutMapping("{id}")
    public User editUser(@RequestBody DTOUser dtouser) {

        User user = new User(dtouser);
        userService.updateUser(user);
        return userService.getUser(user.getId());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}



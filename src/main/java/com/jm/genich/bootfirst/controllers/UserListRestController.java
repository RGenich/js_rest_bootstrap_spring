package com.jm.genich.bootfirst.controllers;

import com.jm.genich.bootfirst.models.DTOUser;
import com.jm.genich.bootfirst.models.Role;
import com.jm.genich.bootfirst.models.User;
import com.jm.genich.bootfirst.service.RoleService;
import com.jm.genich.bootfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserListRestController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping(value = "/userlist")
    public List<User> viewUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("role", new Role());
        model.addAttribute("emptyuser", new DTOUser());
        model.addAttribute("rolesList", roleService.getAllRoles());

        return userService.getAllUsers();
    }

    @PostMapping(value = "/adduser")
    public User addUser (@RequestBody User User) {
        userService.regUser(User);
        return userService.getUserByLogin(User.getLogin());
    }
}

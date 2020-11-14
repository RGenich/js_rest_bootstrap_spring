package com.jm.genich.bootfirst.controllers;

import com.jm.genich.bootfirst.models.Role;
import com.jm.genich.bootfirst.models.User;
import com.jm.genich.bootfirst.service.RoleServiceImpl;
import com.jm.genich.bootfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping(value = "/admin")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("role", new Role());
        model.addAttribute("emptyuser", new User());
        model.addAttribute("rolesList", roleService.getAllRoles());
        return "admin";
    }

}
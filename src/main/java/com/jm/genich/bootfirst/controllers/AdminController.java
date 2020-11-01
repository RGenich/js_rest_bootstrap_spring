package com.jm.genich.bootfirst.controllers;

import com.jm.genich.bootfirst.models.Role;
import com.jm.genich.bootfirst.models.User;
import com.jm.genich.bootfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.showUsers());
        model.addAttribute("role", new Role());
        model.addAttribute("user", new User());

        return "admin";
    }

    @PostMapping(value = "/admin")
    public String saveChanges(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}





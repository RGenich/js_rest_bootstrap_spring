package com.jm.genich.bootfirst.controllers;

import com.jm.genich.bootfirst.models.Role;
import com.jm.genich.bootfirst.models.User;
import com.jm.genich.bootfirst.service.RoleServiceImpl;
import com.jm.genich.bootfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

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
//        model.addAttribute("stringRoleList", user.getRoles().stream().map(t -> t.toString()).collect(Collectors.toList()));

        return "admin";
    }

    @PostMapping(value = "/admin")
    public String saveChanges(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}





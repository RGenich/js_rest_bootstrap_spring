package com.jm.genich.bootfirst.controllers;

import com.jm.genich.bootfirst.models.User;
import com.jm.genich.bootfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(RedirectAttributes attributes, @ModelAttribute User user) {
        userService.addUser(user);
        attributes.addAttribute("id", user.getId());

        return "redirect:/user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, HttpServletRequest request) {
        model.addAttribute("error", request.getParameterMap().containsKey("error"));
        return "login";
    }
}



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

    @GetMapping(value = "/reguser")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "reguser";
    }

    @PostMapping(value = "/reguser")
    public String registerUser(RedirectAttributes attributes, @ModelAttribute User user, HttpServletRequest request) {
        userService.regUser(user);
        attributes.addAttribute("id", user.getId());
        return request.isUserInRole("ROLE_ADMIN") ?
                "redirect:/admin" :
                "redirect:/user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, HttpServletRequest request) {
        model.addAttribute("error", request.getParameterMap().containsKey("error"));
        return "login";
    }
}



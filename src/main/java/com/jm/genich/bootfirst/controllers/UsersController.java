package com.jm.genich.bootfirst.controllers;

import com.jm.genich.bootfirst.exceptions.NotFoundException;
import com.jm.genich.bootfirst.models.User;
import com.jm.genich.bootfirst.service.RoleService;
import com.jm.genich.bootfirst.service.RoleServiceImpl;
import com.jm.genich.bootfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/save")
    public String saveChanges(@ModelAttribute User user, HttpServletRequest request, Model model) {
        userService.updateUser(user);
        model.addAttribute("saved", true);
        return "user";
    }

    @GetMapping(value = "/user")
    public String showUserDetails(Model model, @RequestParam(required = false) Long id, HttpServletRequest request,
                                  Authentication authentication, RedirectAttributes ra) {

        User currentUser = (User) authentication.getPrincipal();
        if (!request.isUserInRole("ROLE_ADMIN") && id != currentUser.getId()) {
            ra.addAttribute("id", currentUser.getId());

            return "redirect:/user";
        }
        if (!userService.existsById(id)) {
            throw new NotFoundException();
        }
        User user = userService.getUser(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("stringRoleList", user.getRoles().stream().map(t -> t.toString()).collect(Collectors.toList()));

        return "user";
    }

    @GetMapping(value = "/")
    public String justRedirect() {
        return "redirect:/admin";
    }
}





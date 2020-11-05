package com.jm.genich.bootfirst.service;

import com.jm.genich.bootfirst.dao.RoleDAO;
import com.jm.genich.bootfirst.dao.RoleDAOImpl;
import com.jm.genich.bootfirst.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private static List<Role> allRoles = Arrays.asList(new Role("ROLE_ADMIN"),
                                                        new Role("ROLE_USER"));
    @Autowired
    RoleDAO roleDAO;

    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }

    public void createRoles() {
        for (Role role : allRoles
        ) {
            roleDAO.createRoles(role);
        }
    }

    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}

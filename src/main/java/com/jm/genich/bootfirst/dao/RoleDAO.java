package com.jm.genich.bootfirst.dao;


import com.jm.genich.bootfirst.models.Role;

import java.util.List;

public interface RoleDAO {
    Role getRoleByName (String name);
    void createRoles (Role role);
    List<Role> getAllRoles();
}

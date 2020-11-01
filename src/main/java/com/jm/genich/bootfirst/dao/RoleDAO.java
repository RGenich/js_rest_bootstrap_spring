package com.jm.genich.bootfirst.dao;


import com.jm.genich.bootfirst.models.Role;

public interface RoleDAO {
    Role getRoleByName (String name);
    void createRoles (String roleName);
}

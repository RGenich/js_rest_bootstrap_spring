package com.jm.genich.bootfirst.service;

import com.jm.genich.bootfirst.models.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);
    void createRoles();
    List<String> getAllRoles();

}

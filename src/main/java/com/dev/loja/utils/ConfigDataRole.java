package com.dev.loja.utils;

import com.dev.loja.model.role.Role;
import com.dev.loja.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ConfigDataRole {

    @Autowired
    RoleService roleService;

    @PostConstruct
    private void addDataRole(){
        Role role0 = new Role();
        role0.setRoleNome("ROLE_USER");
        roleService.save(role0);
        role0.setRoleNome("ROLE_ADMIN");
        roleService.save(role0);
    }
}

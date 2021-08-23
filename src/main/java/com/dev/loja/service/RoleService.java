package com.dev.loja.service;

import com.dev.loja.model.role.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findByRole(String role);
    void save(Role role);
    void delete(Role role);
}

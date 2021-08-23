package com.dev.loja.service.serviceImpl;

import com.dev.loja.model.role.Role;
import com.dev.loja.repository.RoleRepository;
import com.dev.loja.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByRole(String role) {
        if(roleRepository.findById(role).isPresent()){
            return roleRepository.findById(role).get();
        }
        return null;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}

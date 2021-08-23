package com.dev.loja.service;

import com.dev.loja.model.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface PrincipalUserService {
    Usuario getUser();
    void atualizarSecurity();
}

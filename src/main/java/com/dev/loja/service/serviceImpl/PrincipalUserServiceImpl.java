package com.dev.loja.service.serviceImpl;

import com.dev.loja.model.usuario.Usuario;
import com.dev.loja.service.PrincipalUserService;
import com.dev.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrincipalUserServiceImpl implements PrincipalUserService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Usuario getUser() {
        return usuarioService.findByLogin(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }

    @Override
    public void atualizarSecurity() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(getUser().getRole());

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                auth.getPrincipal(),
                auth.getCredentials(),
                grantedAuthorityList)
        );
    }
}

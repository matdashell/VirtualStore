package com.dev.loja.security;

import com.dev.loja.model.usuario.Usuario;
import com.dev.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.findByLogin(s);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado!.");
        }

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getRole());
    }
}

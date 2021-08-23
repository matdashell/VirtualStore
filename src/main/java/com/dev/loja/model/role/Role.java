package com.dev.loja.model.role;

import com.dev.loja.model.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private String roleNome;

    @ManyToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    public String getRoleNome() {
        return roleNome;
    }

    public void setRoleNome(String roleNome) {
        this.roleNome = roleNome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void addUser(Usuario usuario){
        try{
            getUsuarios().add(usuario);
        }catch (NullPointerException e){
            setUsuarios(Collections.singletonList(usuario));
        }
    }

    @Override
    public String getAuthority() {
        return roleNome;
    }
}

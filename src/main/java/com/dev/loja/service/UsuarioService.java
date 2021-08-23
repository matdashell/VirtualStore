package com.dev.loja.service;

import com.dev.loja.model.loja.Produto;
import com.dev.loja.model.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findByLogin(String login);
    Usuario findByNome(String nome);
    boolean userExist(String login);
    void save(Usuario usuario);
    void delete(Usuario usuario);
    List<Produto> findProdutosCarrinho(String login);
}

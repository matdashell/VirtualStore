package com.dev.loja.service.serviceImpl;

import com.dev.loja.model.loja.Produto;
import com.dev.loja.model.usuario.Usuario;
import com.dev.loja.repository.UsuarioRepository;
import com.dev.loja.service.ProdutoService;
import com.dev.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
     private ProdutoService produtoService;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario findByLogin(String login) {
        if(usuarioRepository.findById(login).isPresent()){
            return usuarioRepository.findById(login).get();
        }
        return null;
    }

    @Override
    public Usuario findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    @Override
    public boolean userExist(String login) {
        return usuarioRepository.existsById(login.toLowerCase());
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<Produto> findProdutosCarrinho(String login) {
        return usuarioRepository.getProdutosCarrinho(login)
                .stream()
                .map(id -> produtoService.findById(id))
                .collect(Collectors.toList());
    }
}

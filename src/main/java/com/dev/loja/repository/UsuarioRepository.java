package com.dev.loja.repository;

import com.dev.loja.model.usuario.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    @Query(value = "SELECT * FROM usuario WHERE nome_usuario = ?1", nativeQuery = true)
    Usuario findByNome(String nome);

    @Query(value =
            "SELECT id "
            +"FROM produto "
            +"JOIN carrinho_usuario AS c ON c.produto_id = produto.id AND c.usuario_login_carrinho = ?1"
    ,nativeQuery = true)
    List<Long> getProdutosCarrinho(String login);
}

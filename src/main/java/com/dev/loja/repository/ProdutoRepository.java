package com.dev.loja.repository;

import com.dev.loja.model.loja.CategoriaProduto;
import com.dev.loja.model.loja.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Query(value="SELECT * FROM produto WHERE unidades > 0",nativeQuery = true)
    List<Produto> getProdutosComEstoque();

    @Query(value="SELECT * FROM produto WHERE categoria_id = ?1 and unidades > 0",nativeQuery = true)
    List<Produto> getProdutosCategoriaComEstoque(String categoria);

    @Query(value="SELECT * FROM produto WHERE nome LIKE %?1%", nativeQuery = true)
    List<Produto> pesquisarProduto(String pesquisa);
}

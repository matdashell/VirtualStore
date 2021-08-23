package com.dev.loja.repository;

import com.dev.loja.model.avaliacao.ComentarioProduto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioProdutoRepository extends CrudRepository<ComentarioProduto, Long> {
}

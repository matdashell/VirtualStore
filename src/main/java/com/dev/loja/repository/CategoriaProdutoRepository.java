package com.dev.loja.repository;

import com.dev.loja.model.loja.CategoriaProduto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProdutoRepository extends CrudRepository<CategoriaProduto, String> {
}

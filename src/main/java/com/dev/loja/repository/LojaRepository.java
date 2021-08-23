package com.dev.loja.repository;

import com.dev.loja.model.loja.Loja;
import com.dev.loja.model.loja.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Long> {

    //obter lista de id dos produtos presentes na loja
    @Query(value = "SELECT * FROM produto WHERE loja_id = ?1", nativeQuery = true)
    List<Produto> findAllProdutosLoja(long idLoja);
}

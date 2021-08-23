package com.dev.loja.service;

import com.dev.loja.model.loja.CategoriaProduto;
import com.dev.loja.model.loja.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoService {
    List<Produto> findAll();
    List<Produto> findAtivos();
    List<Produto> findCategoriaAtivos(String categoriaProduto);
    List<Produto> pesquisarProduto(String pesquisa);
    Produto findById(long id);
    void save(Produto produto);
    void delete(Produto produto);
}

package com.dev.loja.service;

import com.dev.loja.model.loja.CategoriaProduto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaProdutoService {
    List<CategoriaProduto> findAll();
    CategoriaProduto findById(String id);
    void save(CategoriaProduto categoriaProduto);
    void delete(CategoriaProduto categoriaProduto);
}

package com.dev.loja.service;

import com.dev.loja.model.loja.Loja;
import com.dev.loja.model.loja.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LojaService {
    List<Loja> findAll();
    List<Produto> findAllProdutosLoja(Loja loja);
    Loja findById(long id);
    void save(Loja loja);
    void delete(Loja loja);
}

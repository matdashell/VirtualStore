package com.dev.loja.service;

import com.dev.loja.model.avaliacao.ComentarioProduto;
import org.springframework.stereotype.Service;

@Service
public interface ComentarioProdutoService {
    void save(ComentarioProduto comentarioProduto);
}

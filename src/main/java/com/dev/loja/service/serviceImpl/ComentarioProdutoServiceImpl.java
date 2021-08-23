package com.dev.loja.service.serviceImpl;

import com.dev.loja.model.avaliacao.ComentarioProduto;
import com.dev.loja.repository.ComentarioProdutoRepository;
import com.dev.loja.service.ComentarioProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioProdutoServiceImpl implements ComentarioProdutoService {

    @Autowired
    ComentarioProdutoRepository comentarioProdutoRepository;

    @Override
    public void save(ComentarioProduto comentarioProduto) {
        comentarioProdutoRepository.save(comentarioProduto);
    }
}

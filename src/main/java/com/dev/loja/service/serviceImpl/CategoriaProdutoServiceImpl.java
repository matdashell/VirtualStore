package com.dev.loja.service.serviceImpl;

import com.dev.loja.model.loja.CategoriaProduto;
import com.dev.loja.repository.CategoriaProdutoRepository;
import com.dev.loja.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProdutoServiceImpl implements CategoriaProdutoService {

    @Autowired
    CategoriaProdutoRepository categoriaProdutoRepository;

    @Override
    public List<CategoriaProduto> findAll() {
        return (List<CategoriaProduto>) categoriaProdutoRepository.findAll();
    }

    @Override
    public CategoriaProduto findById(String id) {
        if(categoriaProdutoRepository.findById(id).isPresent()){
            return categoriaProdutoRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void save(CategoriaProduto categoriaProduto) {
        categoriaProdutoRepository.save(categoriaProduto);
    }

    @Override
    public void delete(CategoriaProduto categoriaProduto) {
        categoriaProdutoRepository.delete(categoriaProduto);
    }
}

package com.dev.loja.utils;

import com.dev.loja.model.loja.CategoriaProduto;
import com.dev.loja.model.loja.Loja;
import com.dev.loja.model.loja.Produto;
import com.dev.loja.service.CategoriaProdutoService;
import com.dev.loja.service.LojaService;
import com.dev.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.DrbgParameters;
import java.util.Random;

@Configuration
public class ConfigDataProduto {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @Autowired
    private LojaService lojaService;

    @PostConstruct
    private void addProduto(){

        int len = categoriaProdutoService.findAll().size();
        Loja loja = new Loja();

        lojaService.save(loja);

        for(int i = 0; i < 120; i++){
            Produto produto = new Produto();
            produto.setLoja(loja);
            produto.setNome("produto " + i);
            produto.setDescricao("Descrição produto" + i);
            produto.setCategoria(categoriaProdutoService.findAll().get((int) (Math.random() * len)));
            produto.setUnidades(Math.round(Math.random()*1000));
            produto.setPreco(Math.round(Math.random()*1000));
            produtoService.save(produto);
        }
    }
}

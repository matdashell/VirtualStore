package com.dev.loja.utils;

import com.dev.loja.model.loja.CategoriaProduto;
import com.dev.loja.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ConfigDataCategoriaProduto {

    @Autowired
    CategoriaProdutoService categoriaProdutoService;

    @PostConstruct
    private void configCategoriaProduto(){
        String[] categorias = {"Camisas", "Bonés", "Acessórios", "outra categoria", "bolsa"};

        for(String cat : categorias){
            CategoriaProduto categoriaProduto = new CategoriaProduto();
            categoriaProduto.setId(cat);
            categoriaProdutoService.save(categoriaProduto);
        }
    }
}

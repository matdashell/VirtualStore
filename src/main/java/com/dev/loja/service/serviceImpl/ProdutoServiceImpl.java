package com.dev.loja.service.serviceImpl;

import com.dev.loja.model.loja.CategoriaProduto;
import com.dev.loja.model.loja.Produto;
import com.dev.loja.repository.ProdutoRepository;
import com.dev.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> findAll() {
        return (List<Produto>) produtoRepository.findAll();
    }

    @Override
    public List<Produto> findAtivos() {
        return produtoRepository.getProdutosComEstoque();
    }

    @Override
    public List<Produto> findCategoriaAtivos(String categoriaProduto) {
        return produtoRepository.getProdutosCategoriaComEstoque(categoriaProduto);
    }

    @Override
    public List<Produto> pesquisarProduto(String pesquisa) {
        return produtoRepository.pesquisarProduto(pesquisa);
    }

    @Override
    public Produto findById(long id) {
        if(produtoRepository.findById(id).isPresent()){
            return produtoRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void save(Produto produto) {
        produtoRepository.save(produto);
    }

    @Override
    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
}

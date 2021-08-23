package com.dev.loja.service.serviceImpl;

import com.dev.loja.model.loja.Loja;
import com.dev.loja.model.loja.Produto;
import com.dev.loja.repository.LojaRepository;
import com.dev.loja.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    @Override
    public List<Loja> findAll() {
        return (List<Loja>) lojaRepository.findAll();
    }

    @Override
    public List<Produto> findAllProdutosLoja(Loja loja) {
        return lojaRepository.findAllProdutosLoja(loja.getId());
    }

    @Override
    public Loja findById(long id) {
        if(lojaRepository.findById(id).isPresent()) {
            return lojaRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void save(Loja loja) {
        lojaRepository.save(loja);
    }

    @Override
    public void delete(Loja loja) {
        lojaRepository.delete(loja);
    }
}

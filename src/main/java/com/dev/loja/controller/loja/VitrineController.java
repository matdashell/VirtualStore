package com.dev.loja.controller.loja;

import com.dev.loja.model.loja.Produto;
import com.dev.loja.service.CategoriaProdutoService;
import com.dev.loja.service.ProdutoService;
import com.dev.loja.utils.objthymeleaf.Contador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VitrineController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    //request padrao
    @RequestMapping(value = "/vitrine", method = RequestMethod.GET)
    public String vitrineGet(){
        return "redirect:/vitrine/0";
    }

    //request padrao
    @RequestMapping(value = "/vitrine", method = RequestMethod.POST)
    public String vitrinePost(){
        return "redirect:/vitrine/0";
    }

    //mostrar itens por categoria
    @RequestMapping(value = "/vitrine/{categoriaID}/{pos}", method = RequestMethod.GET)
    public ModelAndView vitrinePorCategoria(@PathVariable("categoriaID") String cat, @PathVariable("pos") long pos){

        List<Produto> listProdutos = produtoService.findCategoriaAtivos(cat);
        int sizePage = listProdutos.size()/12;

        ModelAndView modelAndView = new ModelAndView("virtualStore/vitrine");

        if(pos < 0){pos = 0;}
        if(pos > sizePage){pos = sizePage;}

        modelAndView.addObject("categorias", categoriaProdutoService.findAll());
        modelAndView.addObject("cont", new Contador());
        modelAndView.addObject("sizePage", sizePage);
        modelAndView.addObject("page", pos);
        modelAndView.addObject("produtos",
                listProdutos
                        .stream()
                        .skip(pos*12)
                        .limit(12)
                        .collect(Collectors.toList())
        );
        return modelAndView;
    }

    //mostrar todos os produtos
    @RequestMapping(value = "/vitrine/{pos}", method = RequestMethod.GET)
    public ModelAndView vitrine(@PathVariable("pos") long pos){

        List<Produto> listProdutos = produtoService.findAtivos();
        int sizePage = listProdutos.size()/12;

        ModelAndView modelAndView = new ModelAndView("virtualStore/vitrine");

        if(pos < 0){pos = 0;}
        if(pos > sizePage){pos = sizePage;}

        modelAndView.addObject("categorias", categoriaProdutoService.findAll());
        modelAndView.addObject("cont", new Contador());
        modelAndView.addObject("sizePage", sizePage);
        modelAndView.addObject("page", pos);
        modelAndView.addObject("produtos",
               listProdutos
                .stream()
                .skip(pos*12)
                .limit(12)
                .collect(Collectors.toList())
        );
        return modelAndView;
    }

    //pesquisar produto por nome
    @RequestMapping(value = "/vitrine/qry/{pos}/{pesquisa}", method = RequestMethod.GET)
    public ModelAndView pesquisarProduto(@PathVariable("pos") long pos, @PathVariable("pesquisa") String pesquisa){

        List<Produto> listProdutos = produtoService.pesquisarProduto(pesquisa);
        System.out.println(listProdutos);
        int sizePage = listProdutos.size()/12;

        ModelAndView modelAndView = new ModelAndView("virtualStore/vitrine");

        if(pos < 0){pos = 0;}
        if(pos > sizePage){pos = sizePage;}

        modelAndView.addObject("categorias", categoriaProdutoService.findAll());
        modelAndView.addObject("pesquisa", pesquisa);
        modelAndView.addObject("cont", new Contador());
        modelAndView.addObject("sizePage", sizePage);
        modelAndView.addObject("page", pos);
        modelAndView.addObject("produtos",
                listProdutos
                        .stream()
                        .skip(pos*12)
                        .limit(12)
                        .collect(Collectors.toList())
        );
        return modelAndView;
    }

    @RequestMapping(value = "/pesquisa", method = RequestMethod.POST)
    public String pesquisa(@RequestParam("pesquisa") String pesquisa){
        return "redirect:/vitrine/qry/0/"+pesquisa;
    }
}

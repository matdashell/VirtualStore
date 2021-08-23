package com.dev.loja.controller.produto;


import com.dev.loja.model.avaliacao.ComentarioProduto;
import com.dev.loja.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class ProdutosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ComentarioProdutoService comentarioProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PrincipalUserService principalUserService;

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
    public ModelAndView visualizarProduto(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("loja/produto/produto");
        boolean bol;

        try{
            bol = principalUserService.getUser().getLoja().getProdutoLoja().contains(produtoService.findById(id));
        }catch (NullPointerException e){
            bol = false;
        }

        modelAndView.addObject("myProduto", bol);
        modelAndView.addObject("produto", produtoService.findById(id));
        bol = usuarioService.findProdutosCarrinho(principalUserService.getUser().getLogin()).contains(produtoService.findById(id));

        modelAndView.addObject("produtoNoCarrinho", bol);
        return modelAndView;
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.POST)
    public String comentarProduto(@PathVariable("id") long id , ComentarioProduto comentarioProduto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            comentarioProduto.setDataComentario(LocalDate.now());
            comentarioProduto.setProduto(produtoService.findById(id));
            comentarioProduto.setUsuario(principalUserService.getUser());

            comentarioProdutoService.save(comentarioProduto);
        }
        return "redirect:/produto/{id}";
    }

    @RequestMapping(value = "/image/iconProd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getProdImage(@PathVariable("id") long id){
        return produtoService.findById(id).getImagem();
    }
}

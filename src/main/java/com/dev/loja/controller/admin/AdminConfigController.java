package com.dev.loja.controller.admin;

import com.dev.loja.model.loja.Loja;
import com.dev.loja.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class AdminConfigController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PrincipalUserService principalUserService;

    @Autowired
    LojaService lojaService;

    @RequestMapping(value = "/admin/loja", method = RequestMethod.GET)
    public ModelAndView minhaLojaGet(){
        ModelAndView modelAndView = new ModelAndView("admin/relatorio");
        modelAndView.addObject("loja", principalUserService.getUser().getLoja());
        modelAndView.addObject("usuario", principalUserService.getUser());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/config", method = RequestMethod.GET)
    public ModelAndView configMinhaLoja(){
        ModelAndView modelAndView = new ModelAndView("admin/configLoja");
        modelAndView.addObject("loja", principalUserService.getUser().getLoja());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/config", method = RequestMethod.POST)
    public String configMinhaLoja(Loja loja, @RequestParam("file") MultipartFile multipartFile) {
        Loja lojaMain = lojaService.findById(loja.getId());
        lojaMain.setNomeLoja(loja.getNomeLoja());
        lojaMain.setDescricaoLoja(loja.getDescricaoLoja());
        if(!multipartFile.isEmpty()){
            try {
                lojaMain.setFoto(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lojaService.save(lojaMain);
        return "redirect:/admin/loja";
    }

    @RequestMapping(value = "/produto/delete/{id}", method = RequestMethod.GET)
    public String deletarProduto(@PathVariable("id") long id){
        produtoService.delete(produtoService.findById(id));
        return "redirect:/admin/loja";
    }

    @RequestMapping(value = "admin/image/iconLoja/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getLojaImage(@PathVariable("id") long id){
        return lojaService.findById(id).getFoto();
    }
}

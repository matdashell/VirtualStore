package com.dev.loja.controller.loja;

import com.dev.loja.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class localLojaController {

    @Autowired
    LojaService lojaService;

    @RequestMapping(value = "/loja/{id}", method = RequestMethod.GET)
    ModelAndView exibirLoja(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("loja/loja");
        modelAndView.addObject("loja", lojaService.findById(id));
        return  modelAndView;
    }
}

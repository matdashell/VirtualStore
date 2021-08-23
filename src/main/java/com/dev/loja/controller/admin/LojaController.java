package com.dev.loja.controller.admin;

import com.dev.loja.model.loja.Loja;
import com.dev.loja.model.loja.Produto;
import com.dev.loja.model.usuario.Usuario;
import com.dev.loja.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

@Controller
public class LojaController {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LojaService lojaService;

    @Autowired
    private PrincipalUserService principalUserService;

    @RequestMapping(value = "/criarLoja", method = RequestMethod.GET)
    public String criarLojaGet(){
        if(principalUserService.getUser().getLoja() != null) {
            return "redirect:/error/loja";
        }
        return "loja/formLoja";
    }

    @RequestMapping(value = "/criarLoja", method = RequestMethod.POST)
    public String criarLojaPost(Loja loja, BindingResult bindingResult, @RequestParam("file") MultipartFile multipartFile){
        if (!bindingResult.hasErrors()) {
            Usuario myUser = principalUserService.getUser();
            myUser.addRole(roleService.findByRole("ROLE_ADMIN"));
            usuarioService.save(myUser);
            loja.setDataCriacaoLoja(LocalDate.now());
            loja.setUsuario(myUser);
            try {
                loja.setFoto(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            lojaService.save(loja);
            principalUserService.atualizarSecurity();

            return "redirect:/admin/loja";
        }
        return "redirect:/criarLoja";
    }

    @RequestMapping(value = "/admin/salvarProduto", method = RequestMethod.GET)
    public ModelAndView salvarProdutoGet(){
        if(principalUserService.getUser().getLoja() != null){
            ModelAndView modelAndView = new ModelAndView("loja/produto/formProduto");
            modelAndView.addObject("categorias", categoriaProdutoService.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:/erro/noStore");
    }

    @RequestMapping(value = "/admin/salvarProduto", method = RequestMethod.POST)
    public String salvarProdutoPost(Produto produto, @RequestParam("file") MultipartFile multipartFile, @RequestParam("categoria_id") String cat){
        if(principalUserService.getUser().getLoja() != null) {
            try {
                System.out.println(Arrays.toString(multipartFile.getBytes()));
                produto.setImagem(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            produto.setCategoria(categoriaProdutoService.findById(cat));
            produto.setLoja(principalUserService.getUser().getLoja());
            produto.setDataPostagem(LocalDate.now());
            produtoService.save(produto);

            return "redirect:/admin/loja";
        }
        return "redirect:/erro/noStore";
    }
}

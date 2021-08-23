package com.dev.loja.controller.user;

import com.dev.loja.model.usuario.Usuario;
import com.dev.loja.service.PrincipalUserService;
import com.dev.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PrincipalUserService principalUserService;

    @RequestMapping(value = "/perfilUser", method = RequestMethod.GET)
    public String perfilPessoal(){
        return "redirect:/perfilUser/"+principalUserService.getUser().getNomeUsuario();
    }

    @RequestMapping(value = "/perfilUser/{user}",method = RequestMethod.GET)
    public ModelAndView visualizarPerfil(@PathVariable("user") String userName){
        Usuario meuUser = principalUserService.getUser();
        ModelAndView modelAndView = new ModelAndView("usuario/perfilUser");
        modelAndView.addObject("sameUserAcc",meuUser.getNomeUsuario().equals(userName));
        modelAndView.addObject("usuario",usuarioService.findByNome(userName));
        return modelAndView;
    }

    @RequestMapping(value = "/perfilUser/configuracoes",method = RequestMethod.GET)
    public ModelAndView configuracoesPerfilGet(){
        ModelAndView modelAndView = new ModelAndView("usuario/configUser");
        modelAndView.addObject("usuario", principalUserService.getUser());
        return modelAndView;
    }

    @RequestMapping(value = "/perfilUser/configuracoes",method = RequestMethod.POST)
    public String configuracoesPerfilPost(Usuario usuario, @RequestParam("file") MultipartFile multipartFile){
        Usuario myUser = principalUserService.getUser();
        myUser.setSenha(new BCryptPasswordEncoder(10).encode(usuario.getSenha()));
        myUser.setNomeUsuario(usuario.getNomeUsuario());
        try {
            myUser.setFoto(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        usuarioService.save(myUser);
        return "redirect:/perfilUser/"+myUser.getNomeUsuario();
    }

    @RequestMapping(value = "/cadastrarLoja", method = RequestMethod.GET)
    public ModelAndView criarLoja(){
        if(principalUserService.getUser().getLoja() == null){
            ModelAndView modelAndView = new ModelAndView("loja/formLoja");
            modelAndView.addObject("usuario", principalUserService.getUser());
        }
        return new ModelAndView("redirect:/error/loja");
    }

    @RequestMapping(value = "/image/iconUser/{usuario}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getUserImagem(@PathVariable("usuario") String usuario){
        return usuarioService.findByNome(usuario).getFoto();
    }
}

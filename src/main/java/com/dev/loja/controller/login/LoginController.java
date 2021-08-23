package com.dev.loja.controller.login;

import com.dev.loja.model.usuario.Usuario;
import com.dev.loja.security.UserDetailsServiceImpl;
import com.dev.loja.service.RoleService;
import com.dev.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login/loginPage";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastroGet(){
        return "login/cadastro";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroPost(Usuario usuario, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("login/cadastro");
            modelAndView.addObject("erro","Preencha os campos corretamente!");
            return modelAndView;
        }

        if(usuarioService.userExist(usuario.getLogin())){
            ModelAndView modelAndView = new ModelAndView("login/cadastro");
            modelAndView.addObject("erro","Login de Usuário já existe!");
            return modelAndView;
        }

        usuario.setSenha(new BCryptPasswordEncoder(10).encode(usuario.getPassword()));
        usuario.setRole(Collections.singletonList(roleService.findByRole("ROLE_USER")));
        usuario.setCredito(1000);

        usuarioService.save(usuario);

        ModelAndView modelAndView = new ModelAndView("login/loginPage");
        modelAndView.addObject("aviso", "Conta criada com sucesso!, efetue o login.");

        return modelAndView;
    }
}

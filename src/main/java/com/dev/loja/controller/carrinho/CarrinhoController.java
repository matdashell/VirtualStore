package com.dev.loja.controller.carrinho;

import com.dev.loja.model.loja.Produto;
import com.dev.loja.model.usuario.Usuario;
import com.dev.loja.service.PrincipalUserService;
import com.dev.loja.service.ProdutoService;
import com.dev.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarrinhoController {

    @Autowired
    private PrincipalUserService principalUserService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

    //retornar os produtos de usuario
    @RequestMapping(value = "/carrinho", method = RequestMethod.GET)
    public ModelAndView carrinhoUsuario(){
        ModelAndView modelAndView = new ModelAndView("usuario/carrinho");
        Usuario myUser = principalUserService.getUser();

        //atualizar carrinho do usuario verificando se os produtos no carrinho ainda possuem estoque
        List<Produto> produtosRemovidos = myUser.getProdutosCarrinho().stream()
                .filter(p -> p.getUnidades() <= 0)
                .collect(Collectors.toList());

        produtosRemovidos.forEach(pro -> myUser.getProdutosCarrinho().remove(pro));

        usuarioService.save(myUser);

        modelAndView.addObject("produtosRemovidos", produtosRemovidos);
        modelAndView.addObject("usuario", principalUserService.getUser());
        return modelAndView;
    }

    //validar compras dos produtos presentes no carrinho do usuario
    @RequestMapping(value = "/carrinho", method = RequestMethod.POST)
    public ModelAndView finalizarCompra(){

        Usuario myUser = principalUserService.getUser();

        //verificar se o usuario possui produtos no carrinho
        if (myUser.getProdutosCarrinho() != null && myUser.getProdutosCarrinho().size() > 0) {

            //variaveis
            List<Produto> produtos = myUser.getProdutosCarrinho().stream().filter(p -> p.getUnidades() > 0).collect(Collectors.toList());
            double valorTotalCompra = produtos.stream().mapToDouble(Produto::getPreco).sum();

            //verificar se o saldo do usuario possui saldo suficiente para efetuar a compra
            if(myUser.getCredito() >= valorTotalCompra) {
                myUser.setCredito(myUser.getCredito() - valorTotalCompra);
                myUser.setNumeroDeCompras(myUser.getNumeroDeCompras() + produtos.size());
                myUser.setTotalGasto(myUser.getTotalGasto() + valorTotalCompra);

                ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");

                //atualizar dados da loja que esta vendendo o produto
                produtos.stream().filter(p -> p.getUnidades() > 0).forEach(p -> {
                    p.getLoja().setProdutosVendidos(p.getLoja().getProdutosVendidos() + 1);
                    p.getLoja().setReceitaTotal(p.getLoja().getReceitaTotal() + p.getPreco());
                    p.setUnidades(p.getUnidades() - 1);

                    //adicionar saldo na conta do usuario dono da loja
                    Usuario donoLoja = usuarioService.findByLogin(p.getLoja().getUsuario().getLogin());
                    donoLoja.addCredito(p.getPreco());
                    usuarioService.save(donoLoja);
                });

                //esvaziar carrinhos do usuario e salvar dados
                usuarioService.save(myUser);

                myUser = principalUserService.getUser();
                myUser.setProdutosCarrinho(null);

                usuarioService.save(myUser);

                return modelAndView;
            }
            //caso de erro saldo insuficiente
            ModelAndView modelAndView = new ModelAndView("usuario/carrinho");
            modelAndView.addObject("erro","Saldo Insuficiente");
            modelAndView.addObject("usuario", myUser);
            return modelAndView;
        }
        //caso erro de carrinho vazio
        ModelAndView modelAndView = new ModelAndView("usuario/carrinho");
        modelAndView.addObject("erro","Sem Produtos no Carrinho");
        modelAndView.addObject("usuario", myUser);
        return modelAndView;
    }

    @RequestMapping(value = "/adicionarCarrinho/{id}", method = RequestMethod.GET)
    public String adicionarCarrinho(@PathVariable("id") long id){
        Usuario myUser = principalUserService.getUser();
        myUser.addProdutoCarrinho(produtoService.findById(id));
        usuarioService.save(myUser);
        return "redirect:/produto/{id}";
    }

    //remover produto na aba do produto
    @RequestMapping(value = "/removerCarrinho/{id}", method = RequestMethod.GET)
    public String removerCarrinho(@PathVariable("id") long id){
        Usuario myUser = principalUserService.getUser();
        myUser.removeProdutoCarrinho(produtoService.findById(id));
        usuarioService.save(myUser);
        return "redirect:/produto/{id}";
    }

    //remover produto na aba do carrinho
    @RequestMapping(value = "/remover/carrinho/{id}", method = RequestMethod.GET)
    public String deletarDoCarrinho(@PathVariable("id") long id){
        Usuario myUser = principalUserService.getUser();
        myUser.removeProdutoCarrinho(produtoService.findById(id));
        usuarioService.save(myUser);
        return "redirect:/carrinho";
    }
}

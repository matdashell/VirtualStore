package com.dev.loja.model.usuario;

import com.dev.loja.model.avaliacao.ComentarioLoja;
import com.dev.loja.model.avaliacao.ComentarioProduto;
import com.dev.loja.model.loja.Loja;
import com.dev.loja.model.loja.Produto;
import com.dev.loja.model.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @NotBlank
    protected String login;

    @NotBlank
    protected String senha;

    @NotBlank
    private String nomeUsuario;

    @OneToOne(mappedBy = "usuario")
    private Loja loja;

    @Lob
    private byte[] foto;

    private int numeroDeCompras;

    private double totalGasto;

    private double credito;

    @OneToMany
    @JoinColumn(name = "usuario_login")
    private List<ComentarioLoja> comentariosLoja;

    @OneToMany
    @JoinColumn(name = "usuario_login")
    private List<ComentarioProduto> comentariosProduto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_usuario",
    joinColumns = @JoinColumn(name = "usuario_login_role", referencedColumnName = "login"),
    inverseJoinColumns = @JoinColumn(name = "nome_role", referencedColumnName = "roleNome"))
    private List<Role> role;

    @ManyToMany()
    @JoinTable(name = "carrinho_usuario",
    joinColumns = @JoinColumn(name = "usuario_login_carrinho", referencedColumnName = "login"),
    inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id"))
    private List<Produto> produtosCarrinho;

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public int getNumeroDeCompras() {
        return numeroDeCompras;
    }

    public void setNumeroDeCompras(int numeroDeCompras) {
        this.numeroDeCompras = numeroDeCompras;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public byte[] getFoto() {
        if(foto == null){
            return null;
        }
        return foto;
    }

    public void addCredito(double credito){
        this.credito += credito;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public List<ComentarioLoja> getComentariosLoja() {
        return comentariosLoja;
    }

    public void setComentariosLoja(List<ComentarioLoja> comentariosLoja) {
        this.comentariosLoja = comentariosLoja;
    }

    public List<ComentarioProduto> getComentariosProduto() {
        return comentariosProduto;
    }

    public void setComentariosProduto(List<ComentarioProduto> comentariosProduto) {
        this.comentariosProduto = comentariosProduto;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public void addRole(Role role){
        try {
            getRole().add(role);
        }catch (Exception e){
            setRole(Collections.singletonList(role));
        }
    }

    public List<Produto> getProdutosCarrinho() {
        return produtosCarrinho;
    }

    public double getValorTotalCarrinho(){
        return produtosCarrinho.stream().mapToDouble(Produto::getPreco).sum();
    }

    public void setProdutosCarrinho(List<Produto> produtosCarrinho) {
        this.produtosCarrinho = produtosCarrinho;
    }

    public void addProdutoCarrinho(Produto produto){
        try{
            getProdutosCarrinho().add(produto);
        }catch (Exception e){
            setProdutosCarrinho(Collections.singletonList(produto));
        }
    }

    public boolean carrinhoIsEmpty(){
        return this.produtosCarrinho.size() == 0;
    }

    public void removeProdutoCarrinho(Produto produto){
        produtosCarrinho.remove(produto);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

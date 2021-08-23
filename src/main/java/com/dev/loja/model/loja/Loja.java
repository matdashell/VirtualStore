package com.dev.loja.model.loja;

import com.dev.loja.model.avaliacao.ComentarioLoja;
import com.dev.loja.model.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String nomeLoja;

    @NotBlank
    private String descricaoLoja;

    private LocalDate dataCriacaoLoja;

    @Lob
    private byte[] foto;

    private double receitaTotal;

    private long produtosVendidos;

    @OneToMany
    @JoinColumn(name = "loja_id")
    private List<Produto> produtoLoja;

    @OneToMany
    @JoinColumn(name = "loja_id")
    private List<ComentarioLoja> comentariosLoja;

    @OneToOne()
    @JoinColumn(name = "usuario_login", referencedColumnName = "login")
    Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getDescricaoLoja() {
        return descricaoLoja;
    }

    public void setDescricaoLoja(String descricaoLoja) {
        this.descricaoLoja = descricaoLoja;
    }

    public LocalDate getDataCriacaoLoja() {
        return dataCriacaoLoja;
    }

    public void setDataCriacaoLoja(LocalDate dataCriacaoLoja) {
        this.dataCriacaoLoja = dataCriacaoLoja;
    }

    public List<Produto> getProdutoLoja() {
        return produtoLoja;
    }

    public void setProdutoLoja(List<Produto> produtoLoja) {
        this.produtoLoja = produtoLoja;
    }

    public List<ComentarioLoja> getComentariosLoja() {
        return comentariosLoja;
    }

    public int getComentariosSize(){
        return getComentariosLoja().size();
    }

    public void setComentariosLoja(List<ComentarioLoja> comentariosLoja) {
        this.comentariosLoja = comentariosLoja;
    }

    public Usuario getUsuario() {

        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public void setReceitaTotal(long receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public long getProdutosVendidos() {
        return produtosVendidos;
    }

    public void setProdutosVendidos(long produtosVendidos) {
        this.produtosVendidos = produtosVendidos;
    }
}

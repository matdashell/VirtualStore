package com.dev.loja.model.avaliacao;

import com.dev.loja.model.loja.Produto;
import com.dev.loja.model.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class ComentarioProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String comentario;

    private LocalDate dataComentario;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(LocalDate dataComentario) {
        this.dataComentario = dataComentario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNomeUsuario(){
        return getUsuario().getNomeUsuario();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

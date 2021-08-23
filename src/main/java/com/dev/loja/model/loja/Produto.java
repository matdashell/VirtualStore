package com.dev.loja.model.loja;

import com.dev.loja.model.avaliacao.ComentarioProduto;
import com.dev.loja.model.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private long unidades;

    @NotNull
    private double preco;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private LocalDate dataPostagem;

    @NotNull
    @Lob
    private byte[] imagem;

    @NotNull
    @ManyToOne()
    private Loja loja;

    @OneToMany
    @JoinColumn(name = "produto_id")
    private List<ComentarioProduto> cometariosProduto;

    @ManyToMany(mappedBy = "produtosCarrinho")
    private List<Usuario> usuariosCarrinho;

    @ManyToOne
    private CategoriaProduto categoria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUnidades() {
        return unidades;
    }

    public void setUnidades(long unidades) {
        this.unidades = unidades;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public List<ComentarioProduto> getCometariosProduto() {
        return cometariosProduto;
    }

    public boolean produtoContainsComentario(){
        return cometariosProduto.size() > 0;
    }

    public byte[] getImagem() {
        if(imagem == null){
            return null;
        }
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public void setCometariosProduto(List<ComentarioProduto> cometariosProduto) {
        this.cometariosProduto = cometariosProduto;
    }

    public List<Usuario> getUsuariosCarrinho() {
        return usuariosCarrinho;
    }

    public void setUsuariosCarrinho(List<Usuario> usuariosCarrinho) {
        this.usuariosCarrinho = usuariosCarrinho;
    }

    public String getCategoria() {
        try {
            return categoria.getId();
        }catch (Exception e){
            return "null";
        }
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }
}

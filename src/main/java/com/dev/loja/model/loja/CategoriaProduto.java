package com.dev.loja.model.loja;

import javax.persistence.*;
import java.util.List;

@Entity
public class CategoriaProduto {

    @Id
    private String id;

    @OneToMany
    @JoinColumn(name = "categoria_id")
    private List<Produto> produto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return this.id;
    }
}

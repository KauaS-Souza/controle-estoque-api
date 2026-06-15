package com.biolab.controleestoquematerial.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeProduto;
    private double preco;
    private String unidadeMedida;
    private int quantidade;
    private String categoria;

    public Produto() {

    }

    public Produto(double preco, String nomeProduto, String unidadeMedida, int quantidade, String categoria) {
        this.preco = preco;
        this.nomeProduto = nomeProduto;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public Produto(long id, String nomeProduto, double preco, String unidadeMedida, int quantidade, String categoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", preco=" + preco +
                ", unidadeMedida='" + unidadeMedida + '\'' +
                ", quantidade=" + quantidade +
                ", categoria='" + categoria + '\'' +
                '}';
    }

}

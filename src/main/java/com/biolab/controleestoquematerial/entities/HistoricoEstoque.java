package com.biolab.controleestoquematerial.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "historico_estoque")
public class HistoricoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    private int quantidadeMovimentacao;
    private LocalDate dataMovimentacao;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public HistoricoEstoque(){

    }

    public HistoricoEstoque(String tipo, int quantidadeMovimentacao, LocalDate dataMovimentacao, Produto produto) {
        this.tipo = tipo;
        this.quantidadeMovimentacao = quantidadeMovimentacao;
        this.dataMovimentacao = dataMovimentacao;
        this.produto = produto;
    }

    public HistoricoEstoque(long id, String tipo, int quantidadeMovimentacao, LocalDate dataMovimentacao, Produto produto) {
        this.id = id;
        this.tipo = tipo;
        this.quantidadeMovimentacao = quantidadeMovimentacao;
        this.dataMovimentacao = dataMovimentacao;
        this.produto = produto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeMovimentacao() {
        return quantidadeMovimentacao;
    }

    public void setQuantidadeMovimentacao(int quantidadeMovimentacao) {
        this.quantidadeMovimentacao = quantidadeMovimentacao;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "HistoricoEstoque{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", quantidadeMovimentacao=" + quantidadeMovimentacao +
                ", dataMovimentacao=" + dataMovimentacao +
                ", produto=" + produto +
                '}';
    }
}

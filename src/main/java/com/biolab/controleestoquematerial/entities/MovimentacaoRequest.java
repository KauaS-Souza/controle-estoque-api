package com.biolab.controleestoquematerial.entities;

public class MovimentacaoRequest {
    private long produtoId;
    private int quantidade;

    public MovimentacaoRequest(){

    }

    public MovimentacaoRequest(int quantidade) {
        this.quantidade = quantidade;
    }

    public MovimentacaoRequest(long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public long getProdutoId() {
        return produtoId;
    }
    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}

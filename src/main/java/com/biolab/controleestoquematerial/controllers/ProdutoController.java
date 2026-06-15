package com.biolab.controleestoquematerial.controllers;

import com.biolab.controleestoquematerial.repositories.ProdutoRepository;

public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

}

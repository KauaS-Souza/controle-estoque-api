package com.biolab.controleestoquematerial.controllers;

import com.biolab.controleestoquematerial.entities.HistoricoEstoque;
import com.biolab.controleestoquematerial.entities.MovimentacaoRequest;
import com.biolab.controleestoquematerial.entities.Produto;
import com.biolab.controleestoquematerial.repositories.HistoricoEstoqueRepository;
import com.biolab.controleestoquematerial.repositories.ProdutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    private final HistoricoEstoqueRepository historicoEstoqueRepository;

    public ProdutoController(ProdutoRepository produtoRepository, HistoricoEstoqueRepository historicoEstoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.historicoEstoqueRepository = historicoEstoqueRepository;
    }

    @PostMapping
    public String cadastrarProduto(@RequestBody Produto produto) {
        if (produto.getPreco() <= 0) {
            return "Erro: O preço unitário deve ser maior que zero.";
        }

        if (produto.getQuantidade() < 0) {
            return "Erro: A quantidade não pode ser negativa.";
        }

        if (produto.getCategoria() == null || produto.getCategoria().isEmpty()) {
            return "Erro: A categoria do material é obrigatória.";
        }

        if (produto.getNomeProduto() == null || produto.getNomeProduto().isEmpty()) {
            return "Erro: O nome do produto é obrigatório.";
        }

        Produto p = new Produto(
                produto.getPreco(),
                produto.getNomeProduto(),
                produto.getUnidadeMedida(),
                produto.getQuantidade(),
                produto.getCategoria()
        );

        produtoRepository.save(p);

        return "Produto cadastrado com sucesso!";
    }

    @GetMapping
    public List<Produto> mostrarProduto(){
        List<Produto> produtoList = produtoRepository.findAll();

        return produtoList;
    }

    @PostMapping("/entradas")
    public String registrarEntrada(@RequestBody MovimentacaoRequest request) {
        // 1. Busca o produto no banco pelo ID enviado
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // 2. Validação: A quantidade de entrada deve ser maior que zero
        if (request.getQuantidade() <= 0) {
            return "Erro: A quantidade de entrada deve ser maior que zero.";
        }

        // 3. Atualiza a quantidade atual do produto somando a nova entrada
        int novaQuantidade = produto.getQuantidade() + request.getQuantidade();
        produto.setQuantidade(novaQuantidade);
        produtoRepository.save(produto); // Salva o produto atualizado

        // 4. Cria o registro no histórico de estoque
        HistoricoEstoque historico = new HistoricoEstoque(
                "ENTRADA",
                request.getQuantidade(),
                java.time.LocalDate.now(), // Pega a data de hoje automaticamente
                produto
        );
        historicoEstoqueRepository.save(historico); // Salva a movimentação no banco

        return "Entrada registrada com sucesso! Novo saldo do produto: " + produto.getQuantidade();
    }

    @PostMapping("/saidas")
    public String registrarSaida(@RequestBody MovimentacaoRequest request) {

        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (request.getQuantidade() <= 0) {
            return "Erro: A quantidade de saída deve ser maior que zero.";
        }

        if (produto.getQuantidade() < request.getQuantidade()) {
            return "Erro: Saldo insuficiente em estoque. Saldo atual: " + produto.getQuantidade();
        }

        int novaQuantidade = produto.getQuantidade() - request.getQuantidade();
        produto.setQuantidade(novaQuantidade);
        produtoRepository.save(produto);

        HistoricoEstoque historico = new HistoricoEstoque(
                "SAIDA",
                request.getQuantidade(),
                java.time.LocalDate.now(),
                produto
        );
        historicoEstoqueRepository.save(historico);

        return "Retirada do produto realizada com sucesso! Novo saldo: " + produto.getQuantidade();
    }

    @GetMapping("/saidas")
    public List<HistoricoEstoque> listarSaidas() {
        return historicoEstoqueRepository.findByTipoOrderByDataMovimentacaoDesc("SAIDA");
    }

    @GetMapping("/alertas")
    public List<String> verificarLimitesEstoque() {
        List<String> alertas = new java.util.ArrayList<>();

        List<Produto> todosOsProdutos = produtoRepository.findAll();

            for (Produto p : todosOsProdutos) {

            if (p.getQuantidade() == 0) {
                alertas.add("ALERTA MÍNIMO: O produto '" + p.getNomeProduto() + "' atingiu o limite mínimo (0 unidades). Nível: 0%");
            }

            if (p.getQuantidade() >= 100) {
                double percentual = p.getQuantidade();
                alertas.add("ALERTA MÁXIMO: O produto '" + p.getNomeProduto() + "' atingiu o limite máximo. Quantidade: " + p.getQuantidade() + ". Nível: " + percentual + "%");
            }
        }

        return alertas;
    }

}

package com.biolab.controleestoquematerial.repositories;

import com.biolab.controleestoquematerial.entities.HistoricoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HistoricoEstoqueRepository extends JpaRepository<HistoricoEstoque, Long> {
    List<HistoricoEstoque> findByTipoOrderByDataMovimentacaoDesc(String tipo);
}

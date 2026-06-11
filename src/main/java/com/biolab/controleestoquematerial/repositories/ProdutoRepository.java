package com.biolab.controleestoquematerial.repositories;

import com.biolab.controleestoquematerial.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

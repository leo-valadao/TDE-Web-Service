package com.leonardo.tde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.tde.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
package com.leonardo.tde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.tde.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

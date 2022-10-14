package com.leonardo.tde.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

// Telefone - Tabela TELEFONE
@Data
@Entity(name = "Telefone")
@Table(name = "TELEFONE")
public class Telefone {

    // Atributos
    // Número
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE_PK", nullable = false)
    private String numero;
}

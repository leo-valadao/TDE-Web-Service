package com.leonardo.tde.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Telefone - Tabela TELEFONE
@Entity(name = "Telefone")
@Table(name = "TELEFONE")
public class Telefone {

    // Atributos
    // Número
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE_PK", nullable = false)
    private String numero;

    // Getters e Setters
    // Número
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // To String
    @Override
    public String toString() {
        return "Telefone [numero=" + numero + "]";
    }
}

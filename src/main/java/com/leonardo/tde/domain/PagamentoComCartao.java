package com.leonardo.tde.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PagamentoComCartao extends Pagamento implements Serializable {

    // Número de Parcelas - Integer - Não Nulo
    @Column(name = "NUMERO_PARCELAS", nullable = false)
    private Integer numeroDeParcelas;
}

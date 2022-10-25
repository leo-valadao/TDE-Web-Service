package com.leonardo.tde.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento {
    
    // Número de Parcelas - Integer - Não Nulo
    @Column(name = "NUMERO_PARCELAS", nullable = false)
    private Integer numeroDeParcelas;
}

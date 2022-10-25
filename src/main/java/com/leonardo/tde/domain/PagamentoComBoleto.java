package com.leonardo.tde.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PagamentoComBoleto extends Pagamento {
    
    // Data de Vencimento - Date - Não Nulo
    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private Date dataVencimento;

    // Data de Pagamento - Date - Não Nulo
    @Column(name = "DATA_PAGAMENTO", nullable = false)
    private Date dataPagamento;
}

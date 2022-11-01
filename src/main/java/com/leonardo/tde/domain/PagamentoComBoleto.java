package com.leonardo.tde.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PagamentoComBoleto extends Pagamento implements Serializable {

    // Data de Vencimento - Date - Não Nulo
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private Date dataVencimento;

    // Data de Pagamento - Date - Não Nulo
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "DATA_PAGAMENTO", nullable = false)
    private Date dataPagamento;
}

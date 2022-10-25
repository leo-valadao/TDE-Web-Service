package com.leonardo.tde.domain.enumerable;

public enum EstadoPagamento {

    APROVADO("Aprovado"), EM_ANALISE("Em Análise"), REPROVADO("Reprovado");

    private String tipo;

    private EstadoPagamento(String tipo){
        this.tipo = tipo;
    }
}

package com.leonardo.tde.enumerable;

public enum EstadoPagamento {

    APROVADO(1, "Aprovado"), 
    EM_ANALISE(2, "Em Análise"), 
    REPROVADO(3, "Reprovado");

    private EstadoPagamento(Integer codigo, String tipo){
    }
}

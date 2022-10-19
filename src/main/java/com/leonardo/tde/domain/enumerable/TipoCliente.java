package com.leonardo.tde.domain.enumerable;

public enum TipoCliente {

    PESSOA_JURIDICA(0, "Pessoa Jurídica (PJ)"), PESSOA_FISICA(1, "Pessoa Física (PF)");

    private Integer codigo;
    private String nome;

    private TipoCliente(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
}

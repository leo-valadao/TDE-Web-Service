package com.leonardo.tde.enumerable;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física (PF)"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica (PJ)"),;

    private TipoCliente(Integer codigo, String nome) {
    }

    public boolean equalsIgnoreCase(TipoCliente tipoCliente) {
        return false;
    }
}

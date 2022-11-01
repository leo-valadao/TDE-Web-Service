package com.leonardo.tde.domain.validations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.leonardo.tde.domain.Cliente;
import com.leonardo.tde.enumerable.TipoCliente;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

    public List<Class<?>> getValidationGroups(Cliente cliente) {
        List<Class<?>> groups = new ArrayList<>();

        groups.add(Cliente.class);

        if (cliente != null) {
            if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
                groups.add(PessoaFisica.class);
            } else if (cliente.getTipoCliente() == TipoCliente.PESSOA_JURIDICA) {
                groups.add(PessoaJuridica.class);
            }
        }

        return groups;
    }
}

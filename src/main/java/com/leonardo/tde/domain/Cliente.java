package com.leonardo.tde.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.leonardo.tde.domain.enumerable.TipoCliente;

import lombok.Data;

// Cliente - Tabela CLIENTE
@Data
@Entity(name = "Cliente")
@Table(name = "CLIENTE")
public class Cliente {

    // Atributos
    // ID - Chave Primária - Serial - Long - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE_PK", nullable = false, unique = true)
    private Long id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "O Nome do Cliente é Obrigatório!")
    @Size(max = 50, message = "O Nome do Cliente Deve Conter no Máximo 50 Caracteres!")
    private String nome;

    // E-Mail - String - 50 Caracteres - Único
    @Column(name = "EMAIL", length = 50, unique = true)
    @Email(message = "E-Mail do Cliente Inválido!")
    @Size(max = 50, message = "O E-Mail do Cliente Deve Conter no Máximo 50 Caracteres!")
    private String email;

    // CPF ou CNPJ - String - 14 Caracteres - Não Nulo - Único
    @Column(name = "CPF_OU_CNPJ", length = 14, nullable = false, unique = true)
    @NotEmpty(message = "O CPF ou CNPJ do Cliente é Obrigatório!")
    @Size(min = 11, max = 11, message = "O CPF do Cliente Deve Ter Apenas 11 Digitos!")
    @Size(min = 14, max = 14, message = "O CNPJ do Cliente Deve Ter Apenas 14 Digitos!")
    @CPF(message = "CPF do Cliente Inválido!")
    @CNPJ(message = "CNPJ do Cliente Inválido!")
    private String cpfOuCnpj;

    // Tipo de Cliente - TipoCliente - Enum - Não Nulo
    @Column(name = "TIPO_CLIENTE", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "O Tipo do Cliente é Obrigatório!")
    private TipoCliente tipoCliente;

    // Relacionamentos
    // Endereços - Chave Estrangeira - Endereco - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cliente")
    private List<Endereco> enderecos;

    // Telefones - Chave Estrangeira - Telefone
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "TELEFONE")
    private Set<Telefone> telefones;

    // Pedidos - Chave Estrangeira - Pedido
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Pedido> pedidos;
}

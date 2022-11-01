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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leonardo.tde.domain.validations.PessoaFisica;
import com.leonardo.tde.domain.validations.PessoaJuridica;
import com.leonardo.tde.enumerable.TipoCliente;

import lombok.Data;

// Cliente - Tabela CLIENTE
@Data
@Entity(name = "Cliente")
@Table(name = "CLIENTE")
public class Cliente {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE_PK", nullable = false)
    private Integer id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false)
    @NotEmpty(message = "O Nome do Cliente é Obrigatório!")
    @Size(max = 50, message = "O Nome do Cliente Deve Conter no Máximo 50 Caracteres!")
    private String nome;

    // E-Mail - String - 50 Caracteres - Único
    @Column(name = "EMAIL", length = 50)
    @Email(message = "E-Mail do Cliente Inválido!")
    @Size(max = 50, message = "O E-Mail do Cliente Deve Conter no Máximo 50 Caracteres!")
    private String email;

    // CPF ou CNPJ - String - 14 Caracteres - Não Nulo - Único
    @Column(name = "CPF_OU_CNPJ", length = 14, nullable = false)
    @NotEmpty(message = "O CPF ou CNPJ do Cliente é Obrigatório!")
    @CPF(groups = PessoaFisica.class, message = "CPF do Cliente Inválido!")
    @CNPJ(groups = PessoaJuridica.class, message = "CNPJ do Cliente Inválido!")
    private String cpfOuCnpj;

    // Tipo de Cliente - TipoCliente - Enum - Não Nulo
    @Column(name = "TIPO_CLIENTE", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Tipo do Cliente é Obrigatório!")
    private TipoCliente tipoCliente;

    // Relacionamentos
    // Endereços - Chave Estrangeira - Endereco - Não Nulo
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cliente")
    private List<Endereco> enderecos;

    // Telefones - Chave Estrangeira - Telefone
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "TELEFONE")
    private Set<Telefone> telefones;

    // Pedidos - Chave Estrangeira - Pedido
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Pedido> pedidos;
}

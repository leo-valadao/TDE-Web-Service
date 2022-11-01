package com.leonardo.tde.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

// Endereço - Tabela ENDERECO
@Data
@Entity(name = "Endereco")
@Table(name = "ENDERECO")
public class Endereco {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO_PK", nullable = false, unique = true)
    private Integer id;

    // Logadouro - String - 100 Caracteres - Não Nulo
    @Column(name = "LOGADOURO", length = 100, nullable = false)
    @Size(max = 100, message = "O Logadouro do Endereço Deve Conter no Máximo 100 Caracteres!")
    @NotEmpty(message = "O Logadouro do Endereço é Obrigatório!")
    private String logadouro;

    // Número - String - 10 Caracteres
    @Column(name = "NUMERO", length = 10)
    @Size(max = 10, message = "O Número do Endereço Deve Conter no Máximo 10 Caracteres!")
    private String numero;

    // Complemento - String - 50 Caracteres
    @Column(name = "COMPLEMENTO", length = 50)
    @Size(max = 100, message = "O Complemento do Endereço Deve Conter no Máximo 50 Caracteres!")
    private String complemento;

    // Bairro - String - 30 Caracteres - Não Nulo
    @Column(name = "BAIRRO", length = 30, nullable = false)
    @Size(max = 100, message = "O Bairro do Endereço Deve Conter no Máximo 30 Caracteres!")
    @NotEmpty(message = "O Bairro do Endereço é Obrigatório!")
    private String bairro;

    // CEP - String - 8 Caracteres - Não Nulo
    @Column(name = "CEP", length = 8, nullable = false)
    @Size(min = 8, max = 8, message = "O CEP do Endereço Deve Ter Apenas 8 Digitos!")
    @NotEmpty(message = "O CEP do Endereço é Obrigatório!")
    private String cep;

    // Relacionamentos
    // Cidade - Chave Estrangeira - Cidade - Não Nulo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CIDADE_FK", referencedColumnName = "ID_CIDADE_PK", nullable = false)
    @NotNull(message = "A Cidade do Endereço é Obrigatório!")
    private Cidade cidade;

    // Cliente - Chave Estrangeira - Cliente - Não Nulo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE_PK", nullable = false)
    @NotNull(message = "O Cliente do Endereço é Obrigatório!")
    private Cliente cliente;
}

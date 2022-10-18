package com.leonardo.tde.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

// Produto - Tabela PRODUTO
@Data
@Entity(name = "Produto")
@Table(name = "PRODUTO")
public class Produto {

    // Atributos
    // ID - Chave Primária - Serial - Long - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_PK", nullable = false, unique = true)
    private Long id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "O Nome do Produto é Obrigatório!")
    @Size(max = 50, message = "O Nome do Produto Deve Conter no Máximo 50 Caracteres!")
    private String nome;

    // Preço do Produto - Double(1000000,2) - Não Nulo
    @Column(name = "PRECO", precision = 1, scale = 2)
    @Positive(message = "O Preço Deve Ser Positivo!")
    @Digits(integer = 1000000, fraction = 2, message = "O Preço Deve Ser Maior Que 0!")
    @DecimalMin(inclusive = false, value = "0", message = "O Preço Deve Ser Maior Que Zero!")
    @NotEmpty(message = "O Preço é Obrigatório!")
    private Double preco;

    // Relacionamentos
    // Itens do Pedido - Chave Estrangeira - ItemPedido - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "produto")
    @NotNull
    private List<ItemPedido> itensPedido;

    // Categoria - Chave Estrangeira - Categoria - Não Nulo
    @ManyToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "ID_CATEGORIA_FK", referencedColumnName = "ID_CATEGORIA_PK", nullable = false)
    @NotNull
    private List<Categoria> categorias;
}

package com.leonardo.tde.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Data;

// Itens do Pedido - Tabela ITEM_PEDIDO
@Data
@Entity(name = "ItemPedido")
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {

    // Atributos
    // ID - Chave Primária - Serial - Long - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM_PEDIDO_PK", nullable = false, unique = true)
    private Long id;

    // Desconto - Double(3,2)
    @Column(name = "DESCONTO", precision = 1, scale = 2)
    @Positive(message = "O Desconto Deve Ser Positivo!")
    @Digits(integer = 3, fraction = 2, message = "O Desconto Deve Ser Entre 100 ou 0!")
    @DecimalMin(inclusive = true, value = "0", message = "O Desconto Deve Ser Maior ou Igual a Zero!")
    @DecimalMax(inclusive = true, value = "100", message = "O Desconto Deve Ser Menor ou Igual a 100!")
    private Double desconto;

    // Quantidade - Integer - Não Nulo
    @Column(name = "QUANTIDADE", nullable = false)
    @Positive(message = "A Quantidade Deve Ser Positiva!")
    @NotEmpty(message = "A Quantidade é Obrigatória!")
    private Integer quantidade;

    // Preço dos Itens - Double(1000000,2) - Não Nulo   
    @Column(name = "PRECO_ITENS", precision = 1, scale = 2)
    @Positive(message = "O Preço Deve Ser Positivo!")
    @Digits(integer = 1000000, fraction = 2, message = "O Preço Deve Ser Maior Que 0!")
    @DecimalMin(inclusive = false, value = "0", message = "O Preço Deve Ser Maior Que Zero!")
    @NotEmpty(message = "O Preço é Obrigatório!")
    private Double preco;

    // Relacionamentos
    // Pedido - Chave Estrangeira - Pedido
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemPedido")
    private List<Pedido> pedidos;

    // Produto - Chave Estrangeira - Produto - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemPedido")
    private List<Produto> produtos;

}

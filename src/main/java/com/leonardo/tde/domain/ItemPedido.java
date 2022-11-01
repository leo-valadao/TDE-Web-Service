package com.leonardo.tde.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

// Itens do Pedido - Tabela ITEM_PEDIDO
@Data
@Entity(name = "ItemPedido")
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @EmbeddedId
    private ItemPedidoPK itemPedidoPK;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "ID_ITEM_PEDIDO_PK", nullable = false)
    // private Integer id;

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
    @NotNull(message = "O Preço é Obrigatório!")
    private Double preco;

    // Relacionamentos
    // Pedido - Chave Estrangeira - Pedido
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(value = "pedido")
    @JoinColumn(name = "ID_PEDIDO_FK", nullable = false, referencedColumnName = "ID_PEDIDO_PK")
    @NotNull(message = "O Pedido é Obrigatório!")
    private Pedido pedido;

    // Produto - Chave Estrangeira - Produto - Não Nulo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(value = "produto")
    @JoinColumn(name = "ID_PRODUTO_FK", nullable = false, referencedColumnName = "ID_PRODUTO_PK")
    @NotNull(message = "O Produto é Obrigatório!")
    private Produto produto;
}

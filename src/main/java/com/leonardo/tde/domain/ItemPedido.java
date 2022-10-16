package com.leonardo.tde.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

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

    @Column(name = "DESCONTO", precision = 2, scale = 10)
    @
    private Double desconto;

    private Integer quantidade;

    private Double preco;

    // Relacionamentos
    // Pedido - Chave Estrangeira - Pedido
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemPedido")
    private Pedido pedido;

    // Produto - Chave Estrangeira - Produto - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemPedido")
    private Produto produto;

}

package com.leonardo.tde.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO_FK", referencedColumnName = "ID_PRODUTO_PK")
    Produto produto;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO_FK", referencedColumnName = "ID_PEDIDO_PK")
    Pedido pedido;
}
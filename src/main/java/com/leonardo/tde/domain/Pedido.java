package com.leonardo.tde.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "Pedido")
@Table(name = "PEDIDO")
public class Pedido {

    // Atributos
    // ID - Chave Primária - Serial - Long - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_PK", nullable = false, unique = true)
    private Long id;

    // Instante - Date - Não Nulo
    @Column(name = "INSTANTE", nullable = false)
    @NotEmpty(message = "O Instante do Pedido é Obrigatório!")
    private Date instante;

    // Relacionamentos
    // Cliente - Chave Estrangeira - Cliente - Não Nulo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE_PK", nullable = false)
    @NotNull
    private Cliente cliente;

    // Pagamento - Chave Estrangeira - Pagamento - Não Nulo
    // @OneToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "ID_PAGAMENTO_FK", referencedColumnName = "ID_PAGAMENTO_PK", nullable = false)
    // @NotNull
    // private Pagamento pagamento;

    // Itens do Pedido - Chave Estrangeira - ItemPedido - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "pedido")
    @NotNull
    private List<ItemPedido> itensPedido;

    // Endereço de Entrega - Chave Estrangeira - Endereço - Não Nulo
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ENDERECO_FK", referencedColumnName = "ID_ENDERECO_PK", nullable = false)
    @NotNull
    private Endereco enderecoDeEntrega;
}

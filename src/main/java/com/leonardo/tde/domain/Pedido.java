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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity(name = "Pedido")
@Table(name = "PEDIDO")
public class Pedido {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_PK", nullable = false)
    private Integer id;

    // Instante - Date - Não Nulo
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "INSTANTE", nullable = false)
    @NotNull(message = "O Instante do Pedido é Obrigatório!")
    private Date instante;

    // Relacionamentos
    // Cliente - Chave Estrangeira - Cliente - Não Nulo
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE_PK", nullable = false)
    @NotNull(message = "O Cliente do Pedido é Obrigatório!")
    private Cliente cliente;

    // Pagamento - Chave Estrangeira - Pagamento - Não Nulo
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PAGAMENTO_FK", referencedColumnName = "ID_PAGAMENTO_PK", nullable = false)
    @NotNull(message = "O Pagamento do Pedido é Obrigatório!")
    private Pagamento pagamento;

    // // Itens do Pedido - Chave Estrangeira - ItemPedido - Não Nulo
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "pedido")
    @NotNull(message = "O(s) Item(ns) do Pedido é(são) Obrigatório(s)!")
    private List<ItemPedido> itensPedido;

    // Endereço de Entrega - Chave Estrangeira - Endereço - Não Nulo
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ENDERECO_FK", referencedColumnName = "ID_ENDERECO_PK", nullable = false)
    @NotNull(message = "O Endereço de Entrega do Pedido é Obrigatório!")
    private Endereco enderecoDeEntrega;
}

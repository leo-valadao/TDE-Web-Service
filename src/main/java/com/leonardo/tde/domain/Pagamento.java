package com.leonardo.tde.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.leonardo.tde.enumerable.EstadoPagamento;

import lombok.Data;

// Pagamento - Tabela PAGAMENTO
@Data
@Entity(name = "Pagamento")
@Table(name = "PAGAMENTO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pagamento {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGAMENTO_PK", nullable = false, unique = true)
    private Integer id;

    // Estado de Pagamento - Enumeravel - EstadoPagamento
    @Column(name = "ESTADO_PAGAMENTO", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private EstadoPagamento estado;

    // Relacionamentos
    // Pagamento - Chave Estrangeira - Pagamento
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PEDIDO_FK", referencedColumnName = "ID_PEDIDO_PK", nullable = false)
    @NotNull
    private Pedido pedido;
}

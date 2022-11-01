package com.leonardo.tde.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.tde.domain.ItemPedido;
import com.leonardo.tde.domain.ItemPedidoPK;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}
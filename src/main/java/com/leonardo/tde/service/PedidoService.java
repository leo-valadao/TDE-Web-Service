package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.ItemPedido;
import com.leonardo.tde.domain.Pedido;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.PedidoRepository;

@Service
public class PedidoService {

    // Objetos
    // Repositório do Endereço
    @Autowired
    private PedidoRepository pedidoRepository;

    // Serviço do ItemPedido
    @Autowired
    private ItemPedidoService itensPedidoService;

    // Métodos
    // Buscar Todos os Endereços
    public List<Pedido> todosPedidos() {
        return pedidoRepository.findAll();
    }

    // Buscar Pedido Por ID
    public Pedido pedidoPorId(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido Não Encontrado! ID: " + id));
    }

    // Salvar Pedido
    public void salvarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    // Atualizar Pedido
    public void atualizarPedido(Pedido pedido) {
        if (pedidoRepository.existsById(pedido.getId())) {
            pedidoRepository.saveAndFlush(pedido);
        } else {
            throw new NotFoundException("Pedido Não Encontrado! ID: " + pedido.getId());
        }
    }

    // Excluir Pedido Por ID
    public void excluirPedido(Integer id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Pedido Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Item Pedido do Pedido
    // public Pedido atualizarItemPedidoDoPedido(Integer id, List<ItemPedido> itensPedido) {
    //     if (pedidoRepository.existsById(id)) {
    //         Pedido pedido = pedidoRepository.findById(id).get();
    //         List<ItemPedido> oldItensPedido = pedido.getItensPedido();

    //         pedido.setItensPedido(itensPedido);
    //         this.salvarPedido(pedido);

    //         if (oldItensPedido != null) {
    //             for (ItemPedido itemPedido : oldItensPedido) {
    //                 this.itensPedidoService.excluirItemPedido(itemPedido.getId());
    //             }
    //         }

    //         return pedido;
    //     } else {
    //         throw new NotFoundException("Pedido Não Encontrado! ID: " + id);
    //     }
    // }
}

package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.ItemPedido;
import com.leonardo.tde.domain.Pedido;
import com.leonardo.tde.domain.Produto;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

    // Objetos
    // Repositório do Item Produto
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    // Serviço do Produto
    @Autowired
    private ProdutoService produtoService;

    // Serviço do Pedido
    @Autowired
    private PedidoService pedidoService;

    // Métodos
    // Buscar Todos os Item Pedidos
    public List<ItemPedido> todosItemPedidos() {
        return itemPedidoRepository.findAll();
    }

    // Buscar Item Pedido Por ID
    public ItemPedido itemPedidoPorId(int id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item Pedido Não Encontrado! ID: " + id));
    }

    // Salvar Item Pedido
    public void salvarItemPedido(ItemPedido itemPedido) {
        itemPedidoRepository.save(itemPedido);
    }

    // Atualizar Item Pedido
    public void atualizarItemPedido(ItemPedido itemPedido) {
        if (itemPedidoRepository.existsById(itemPedido.getId())) {
            itemPedidoRepository.saveAndFlush(itemPedido);
        } else {
            throw new NotFoundException("Item Pedido Não Encontrado! ID: " + itemPedido.getId());
        }
    }

    // Excluir Item Pedido Por ID
    public void excluirItemPedido(Integer id) {
        if (itemPedidoRepository.existsById(id)) {
            itemPedidoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Item Pedido Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Produto do Item Pedido
    public ItemPedido atualizarProdutoDoItemPedido(Integer id, Produto produto) {
        if (itemPedidoRepository.existsById(id)) {
            ItemPedido itemPedido = itemPedidoRepository.findById(id).get();
            Produto oldProduto = itemPedido.getProduto();

            itemPedido.setProduto(produto);
            this.salvarItemPedido(itemPedido);

            if (oldProduto != null) {
                this.produtoService.excluirProduto(produto.getId());
            }

            return itemPedido;
        } else {
            throw new NotFoundException("Produto do Item do Pedido Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Pedido do Item Pedido
    public ItemPedido atualizarPedidoDoItemPedido(Integer id, Pedido pedido) {
        if (itemPedidoRepository.existsById(id)) {
            ItemPedido itemPedido = itemPedidoRepository.findById(id).get();
            Pedido oldPedido = itemPedido.getPedido();

            itemPedido.setPedido(pedido);
            this.salvarItemPedido(itemPedido);

            if (oldPedido != null) {
                this.pedidoService.excluirPedido(pedido.getId());
            }

            return itemPedido;
        } else {
            throw new NotFoundException("Pedidos do Item Pedido Não Encontrado! ID: " + id);
        }
    }
}

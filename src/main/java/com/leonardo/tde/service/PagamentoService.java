package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.Pagamento;
import com.leonardo.tde.domain.Pedido;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.PagamentoRepository;

@Service
public class PagamentoService {

    // Objetos
    // Repositório do Pagamento
    @Autowired
    private PagamentoRepository pagamentoRepository;

    // Serviçi do Pedido
    @Autowired
    private PedidoService pedidoService;

    // Métodos
    // Buscar Todos os Pagamentos
    public List<Pagamento> todosPagamentos() {
        return pagamentoRepository.findAll();
    }

    // Buscar Pagamento Por ID
    public Pagamento pagamentoPorId(int id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pagamento Não Encontrado! ID: "+ id));
    }

    // Salvar Pagamento
    public void salvarPagamento(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }

    // Atualizar Pagamento
    public void atualizarPagamento(Pagamento pagamento) {
        if (pagamentoRepository.existsById(pagamento.getId())) {
            pagamentoRepository.save(pagamento);
        } else {
            throw new NotFoundException("Pagamento Não Encontrado! ID: " + pagamento.getId());
        }
    }

    // Excluir Pagamento
    public void excluirPagamento(Integer id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Pagamento Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Pedido do Pagamento
    public Pagamento atualizarPedidoDoPagamento(Integer id, Pedido pedido) {
        if (pagamentoRepository.existsById(id)) {
            Pagamento pagamento = pagamentoRepository.findById(id).get();
            Pedido oldPedido = pagamento.getPedido();

            pagamento.setPedido(pedido);
            this.salvarPagamento(pagamento);

            if (oldPedido != null) {
                this.pedidoService.excluirPedido(pedido.getId());
            }

            return pagamento;
        } else {
            throw new NotFoundException("Pagamento Não Encontrado! ID: "+ id);
        }
    }
}

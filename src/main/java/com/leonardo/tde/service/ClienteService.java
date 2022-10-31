package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.Cliente;
import com.leonardo.tde.domain.Endereco;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.ClienteRepository;

@Service
public class ClienteService {

    // Objetos
    // Repositório do Cliente
    @Autowired
    private ClienteRepository clienteRepository;

    // Serviço do Endereço
    @Autowired
    private EnderecoService enderecoService;

    // Métodos
    // Buscar Todos os Clientes
    public List<Cliente> todosClientes() {
        return clienteRepository.findAll();
    }

    // Buscar Cliente Por ID
    public Cliente clientePorId(int id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente Não Encontrado! ID: " + id));
    }

    // Salvar Cliente
    public void salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // Atualizar Cliente
    public void atualizarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            clienteRepository.saveAndFlush(cliente);
        } else {
            throw new NotFoundException("Cliente Não Encontrado! ID: " + cliente.getId());
        }
    }

    // Excluir Cliente
    public void excluirCliente(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new NotFoundException("Cliente Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Endereços do Cliente
    public Cliente atualizarEnderecosDoCliente(Integer id, List<Endereco> enderecos) {
        if (clienteRepository.existsById(id)) {
            Cliente cliente = clienteRepository.findById(id).get();
            List<Endereco> oldEnderecos = cliente.getEnderecos();

            cliente.setEnderecos(enderecos);
            this.salvarCliente(cliente);

            if (oldEnderecos != null) {
                for (Endereco endereco : oldEnderecos) {
                    this.enderecoService.excluirEndereco(endereco.getId());
                }
            }

            return cliente;
        } else {
            throw new NotFoundException("Cliente Não Encontrado! ID: " + id);
        }
    }
}

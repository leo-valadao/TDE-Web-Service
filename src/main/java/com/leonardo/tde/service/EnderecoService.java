package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.Cliente;
import com.leonardo.tde.domain.Endereco;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.EnderecoRepository;

@Service
public class EnderecoService {

    // Objetos
    // Repositório do Endereço
    @Autowired
    private EnderecoRepository enderecoRepository;

    // Serviço do Cliente
    // @Autowired
    // private ClienteService clienteService;

    // Métodos
    // Buscar Todos os Endereços
    public List<Endereco> todosEnderecos() {
        return enderecoRepository.findAll();
    }

    // Buscar Endereco Por ID
    public Endereco enderecoPorId(int id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereco Não Encontrado! ID: " + id));
    }

    // Salvar Endereco
    public void salvarEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    // Atualizar Endereco
    public void atualizarEndereco(Endereco endereco) {
        if (enderecoRepository.existsById(endereco.getId())) {
            enderecoRepository.saveAndFlush(endereco);
        } else {
            throw new NotFoundException("Endereco Não Encontrado! ID: " + endereco.getId());
        }
    }

    // Excluir Endereco Por ID
    public void excluirEndereco(Integer id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Endereco Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Cliente do Endereco
    // public Endereco atualizarClienteDoEndereco(Integer id, Cliente cliente) {
    //     if (enderecoRepository.existsById(id)) {
    //         Endereco endereco = enderecoRepository.findById(id).get();
    //         Cliente oldCliente = endereco.getCliente();

    //         endereco.setCliente(cliente);
    //         this.salvarEndereco(endereco);

    //         if (oldCliente != null) {
    //             this.clienteService.excluirCliente(cliente.getId());
    //         }

    //         return endereco;
    //     } else {
    //         throw new NotFoundException("Endereco Não Encontrado! ID: " + id);
    //     }
    // }
}

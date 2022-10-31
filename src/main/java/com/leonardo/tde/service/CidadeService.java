package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.Cidade;
import com.leonardo.tde.domain.Estado;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.CidadeRepository;

@Service
public class CidadeService {

    // Objetos
    // Repositório da Cidade
    @Autowired
    private CidadeRepository cidadeRepository;

    // Serviço do Estado
    // @Autowired
    // private EstadoService estadoService;

    // Métodos
    // Buscar Todas as Cidades
    public List<Cidade> todasCidades() {
        return cidadeRepository.findAll();
    }

    // Buscar Cidade Por ID
    public Cidade cidadePorId(int id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cidade Não Encontrada! ID: " + id));
    }

    // Salvar Cidade
    public void salvarCidade(Cidade cidade) {
        cidadeRepository.save(cidade);
    }

    // Atualizar Cidade
    public void atualizarCidade(Cidade cidade) {
        if (cidadeRepository.existsById(cidade.getId())) {
            cidadeRepository.save(cidade);
        } else {
            throw new NotFoundException("Cidade Não Encontrada! ID: " + cidade.getId());
        }
    }
    
    // Excluir Cidade Por ID
    public void excluirCidade(Integer id) {
        if (cidadeRepository.existsById(id)) {
            cidadeRepository.deleteById(id);
        } else {
            throw new NotFoundException("Cidade Não Encontrada! ID: " + id);
        }
    }

    // Atualizar Estado da Cidade
    // public Cidade atualizarEstadoDaCidade(Integer id, Estado estado) {
    //     if (cidadeRepository.existsById(id)) {
    //         Cidade cidade = cidadeRepository.findById(id).get();
    //         Estado oldEstado = cidade.getEstado();

    //         cidade.setEstado(estado);
    //         this.salvarCidade(cidade);

    //         if (oldEstado != null) {
    //             this.estadoService.excluirEstado(estado.getId());
    //         }

    //         return cidade;
    //     } else {
    //         throw new NotFoundException("Cidade Não Encontrada! ID: " + id);
    //     }
    // }
}

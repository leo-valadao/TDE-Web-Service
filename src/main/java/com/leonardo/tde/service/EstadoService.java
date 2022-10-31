package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.Cidade;
import com.leonardo.tde.domain.Estado;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.EstadoRepository;

@Service
public class EstadoService {

    // Objetos
    // Repositório do Estado
    @Autowired
    private EstadoRepository estadoRepository;

    // Serviço da Cidade
    @Autowired
    private CidadeService cidadeService;

    // Métodos
    // Buscar Todos os Estados
    public List<Estado> todosEstados() {
        return estadoRepository.findAll();
    }

    // Buscar Estado Por ID
    public Estado estadoPorId(int id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado Não Encontrado! ID: " + id));
    }

    // Salvar Estado
    public void salvarEstado(Estado estado) {
        estadoRepository.save(estado);
    }

    // Atualizar Estado
    public void atualizarEstado(Estado estado) {
        if (estadoRepository.existsById(estado.getId())) {
            estadoRepository.save(estado);
        } else {
            throw new NotFoundException("Estado Não Encontrado! ID: " + estado.getId());
        }
    }

    // Excluir Estado Por ID
    public void excluirEstado(Integer id) {
        if (estadoRepository.equals(id)) {
            estadoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Estado Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Cidades do Estado
    public Estado atualizarCidadesDoEstado(Integer id, List<Cidade> cidades) {
        if (estadoRepository.existsById(id)) {
            Estado estado = estadoRepository.findById(id).get();
            List<Cidade> oldCidades = estado.getCidades();

            estado.setCidades(cidades);
            this.salvarEstado(estado);

            if (oldCidades != null) {
                for (Cidade cidade : oldCidades) {
                    this.cidadeService.excluirCidade(cidade.getId());
                }
            }

            return estado;
        } else {
            throw new NotFoundException("Estado Não Encontrado! ID: " + id);
        }
    }
}

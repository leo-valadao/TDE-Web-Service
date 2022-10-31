package com.leonardo.tde.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.tde.domain.Estado;
import com.leonardo.tde.domain.model_assembler.EstadoModelAssembler;
import com.leonardo.tde.domain.Cidade;
import com.leonardo.tde.service.EstadoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API do Estado - Versão 1
@RestController
@RequestMapping("/api/v1/estado")
public class EstadoResource {

    // Objetos
    // Serviço do Estado
    @Autowired
    private EstadoService estadoService;

    // Montador de Modelo do Estado
    // @Autowired
    private EstadoModelAssembler estadoModelAssembler = new EstadoModelAssembler();

    // API's
    // Obters Todos os Estados
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Estado>>> obterTodosEstados() {
        List<Estado> estados = estadoService.todosEstados();

        if (!estados.isEmpty()) {
            List<EntityModel<Estado>> listaEntityModelEstados = new ArrayList<EntityModel<Estado>>();

            listaEntityModelEstados.addAll(estados.stream().map(estadoModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Estado>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelEstados);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Estado Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Estado>> obterEstadoPorId(@PathVariable("id") Integer id) {
        Estado estado = estadoService.estadoPorId(id);

        EntityModel<Estado> entityModelEstado = estadoModelAssembler.toModel(estado);

        return ResponseEntity.ok(entityModelEstado);
    }

    // Salvar a Estado
    @PostMapping
    public ResponseEntity<EntityModel<Estado>> salvarEstado(@RequestBody @Valid Estado estado,
            HttpServletRequest request) {
        estadoService.salvarEstado(estado);

        URI uri = linkTo(methodOn(EstadoResource.class).obterEstadoPorId(estado.getId())).withSelfRel().toUri();
        EntityModel<Estado> entityModelEstado = estadoModelAssembler.toModel(estado);

        return ResponseEntity.created(uri).body(entityModelEstado);
    }

    // Atualizar a Estado
    @PutMapping
    public ResponseEntity<EntityModel<Estado>> atualizarEstado(@RequestBody Estado estado) {
        estadoService.estadoPorId(estado.getId());
        estadoService.atualizarEstado(estado);

        EntityModel<Estado> entityModelEstado = estadoModelAssembler.toModel(estado);

        return ResponseEntity.ok(entityModelEstado);
    }

    // Excluir a Estado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstadoPorId(@PathVariable("id") Integer id) {
        estadoService.excluirEstado(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar os Cidades da Estado
    @PatchMapping("/{id}/cidades")
    public ResponseEntity<EntityModel<Estado>> atualizarCidadesDoEstado(@PathVariable("id") Integer idEstado,
            List<Cidade> cidades) {
        Estado estado = estadoService.atualizarCidadesDoEstado(idEstado, cidades);

        EntityModel<Estado> entityModelEstado = estadoModelAssembler.toModel(estado);

        return ResponseEntity.ok(entityModelEstado);
    }
}

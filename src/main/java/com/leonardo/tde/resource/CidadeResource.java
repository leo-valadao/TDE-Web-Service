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

import com.leonardo.tde.domain.Cidade;
import com.leonardo.tde.domain.Estado;
import com.leonardo.tde.domain.model_assembler.CidadeModelAssembler;
import com.leonardo.tde.service.CidadeService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API da Cidade - Versão 1
@RestController
@RequestMapping("/api/v1/cidade")
public class CidadeResource {
    
    // Objetos
    // Serviço da Cidade
    @Autowired
    private CidadeService cidadeService;

    // Montador de Modelo da Cidade
    // @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    // API's
    // Obter Todas as Cidades
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Cidade>>> obterTodasCidades() {
        List<Cidade> cidades = cidadeService.todasCidades();

        if (!cidades.isEmpty()) {
            List<EntityModel<Cidade>> listaEntityModelCidades = new ArrayList<EntityModel<Cidade>>();

            listaEntityModelCidades.addAll(cidades.stream().map(cidadeModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Cidade>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelCidades);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Cidade Por ID
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Cidade>> obterCidadePorId(@PathVariable("id") Integer id) {
        Cidade cidade = cidadeService.cidadePorId(id);
        
        EntityModel<Cidade> entityModelCidade = cidadeModelAssembler.toModel(cidade);

        return ResponseEntity.ok(entityModelCidade);
    }

    // Salvar a Cidade
    @PostMapping
    public ResponseEntity<EntityModel<Cidade>> salvarCidade(@RequestBody @Valid Cidade cidade, HttpServletRequest request) {
        cidadeService.salvarCidade(cidade);

        URI uri = linkTo(methodOn(CidadeResource.class).obterCidadePorId(cidade.getId())).withSelfRel().toUri();
        EntityModel<Cidade> entityModelCidade = cidadeModelAssembler.toModel(cidade);

        return ResponseEntity.created(uri).body(entityModelCidade);
    }

    // Atualizar a Cidade
    @PutMapping
    public ResponseEntity<EntityModel<Cidade>> atualizarCidade(@RequestBody Cidade cidade) {
        cidadeService.cidadePorId(cidade.getId());
        cidadeService.atualizarCidade(cidade);

        EntityModel<Cidade> entityModelCidade = cidadeModelAssembler.toModel(cidade);

        return ResponseEntity.ok(entityModelCidade);
    }

    // Excluir a Cidade
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCidadePorId(@PathVariable("id") Integer id) {
        cidadeService.excluirCidade(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar o Estado da Cidade
    @PatchMapping("/{id}/estado")
    public ResponseEntity<EntityModel<Cidade>> atualizarEstadoDaCidade(@PathVariable("id") Integer idCidade,
            Estado estado) {
        Cidade cidade = cidadeService.atualizarEstadoDaCidade(idCidade, estado);

        EntityModel<Cidade> entityModelCidade = cidadeModelAssembler.toModel(cidade);

        return ResponseEntity.ok(entityModelCidade);
    }
}

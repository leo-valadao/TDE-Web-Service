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

import com.leonardo.tde.domain.Cliente;
import com.leonardo.tde.domain.Endereco;
import com.leonardo.tde.domain.model_assembler.EnderecoModelAssembler;
import com.leonardo.tde.service.EnderecoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API do Endereco - Versão 1
@RestController
@RequestMapping("/api/v1/endereco")
public class EnderecoResource {

    // Objetos
    // Serviço do Endereco
    @Autowired
    private EnderecoService enderecoService;

    // Montador de Modelo do Endereco
    // @Autowired
    private EnderecoModelAssembler enderecoModelAssembler;

    // API's
    // Obter Todos os Enderecos
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Endereco>>> obterTodosEnderecos() {
        List<Endereco> cliente = enderecoService.todosEnderecos();

        if (!cliente.isEmpty()) {
            List<EntityModel<Endereco>> listaEntityModelEnderecos = new ArrayList<EntityModel<Endereco>>();

            listaEntityModelEnderecos.addAll(cliente.stream().map(enderecoModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Endereco>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelEnderecos);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Endereco Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Endereco>> obterEnderecoPorId(@PathVariable("id") Integer id) {
        Endereco endereco = enderecoService.enderecoPorId(id);

        EntityModel<Endereco> entityModelEndereco = enderecoModelAssembler.toModel(endereco);

        return ResponseEntity.ok(entityModelEndereco);
    }

    // Salvar o Endereco
    @PostMapping
    public ResponseEntity<EntityModel<Endereco>> salvarEndereco(@RequestBody @Valid Endereco endereco,
            HttpServletRequest request) {
        enderecoService.salvarEndereco(endereco);

        URI uri = linkTo(methodOn(EnderecoResource.class).obterEnderecoPorId(endereco.getId())).withSelfRel().toUri();
        EntityModel<Endereco> entityModelEndereco = enderecoModelAssembler.toModel(endereco);

        return ResponseEntity.created(uri).body(entityModelEndereco);
    }

    // Atualizar o Endereco
    @PutMapping
    public ResponseEntity<EntityModel<Endereco>> atualizarEndereco(@RequestBody Endereco endereco) {
        enderecoService.enderecoPorId(endereco.getId());
        enderecoService.atualizarEndereco(endereco);

        EntityModel<Endereco> entityModelEndereco = enderecoModelAssembler.toModel(endereco);

        return ResponseEntity.ok(entityModelEndereco);
    }

    // Excluir o Endereco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEnderecoPorId(@PathVariable("id") Integer id) {
        enderecoService.excluirEndereco(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar o Endereço do Endereco
    @PatchMapping("/{id}/cliente")
    public ResponseEntity<EntityModel<Endereco>> atualizarClienteDoEndereco(@PathVariable("id") Integer idEndereco,
            Cliente cliente) {
        Endereco endereco = enderecoService.atualizarClienteDoEndereco(idEndereco, cliente);

        EntityModel<Endereco> entityModelEndereco = enderecoModelAssembler.toModel(endereco);

        return ResponseEntity.ok(entityModelEndereco);
    }
}
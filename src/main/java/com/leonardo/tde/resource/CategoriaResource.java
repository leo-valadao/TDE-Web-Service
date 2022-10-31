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

import com.leonardo.tde.domain.Categoria;
import com.leonardo.tde.domain.Produto;
import com.leonardo.tde.domain.model_assembler.CategoriaModelAssembler;
import com.leonardo.tde.service.CategoriaService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API da Categoria - Versão 1
@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaResource {

    // Objetos
    // Serviço da Categoria
    @Autowired
    private CategoriaService categoriaService;

    // Montador de Modelo da Categoria
    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;

    // API's
    // Obter Todas as Categorias
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Categoria>>> obterTodasCategorias() {
        List<Categoria> categorias = categoriaService.todasCategorias();

        if (!categorias.isEmpty()) {
            List<EntityModel<Categoria>> listaEntityModelCategorias = new ArrayList<EntityModel<Categoria>>();

            listaEntityModelCategorias.addAll(categorias.stream().map(categoriaModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Categoria>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelCategorias);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Categoria Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> obterCategoriaPorId(@PathVariable("id") Integer id) {
        Categoria categoria = categoriaService.categoriaPorId(id);

        EntityModel<Categoria> entityModelCategoria = categoriaModelAssembler.toModel(categoria);

        return ResponseEntity.ok(entityModelCategoria);
    }

    // Salvar a Categoria
    @PostMapping
    public ResponseEntity<EntityModel<Categoria>> salvarCategoria(@RequestBody @Valid Categoria categoria,
            HttpServletRequest request) {
        categoriaService.salvarCategoria(categoria);

        URI uri = linkTo(methodOn(CategoriaResource.class).obterCategoriaPorId(categoria.getId())).withSelfRel().toUri();
        EntityModel<Categoria> entityModelCategoria = categoriaModelAssembler.toModel(categoria);

        return ResponseEntity.created(uri).body(entityModelCategoria);
    }

    // Atualizar a Categoria
    @PutMapping
    public ResponseEntity<EntityModel<Categoria>> atualizarCategoria(@RequestBody Categoria categoria) {
        categoriaService.categoriaPorId(categoria.getId());
        categoriaService.atualizarCategoria(categoria);

        EntityModel<Categoria> entityModelCategoria = categoriaModelAssembler.toModel(categoria);

        return ResponseEntity.ok(entityModelCategoria);
    }

    // Excluir a Categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoriaPorId(@PathVariable("id") Integer id) {
        categoriaService.excluirCategoria(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar os Produtos da Categoria
    @PatchMapping("/{id}/produtos")
    public ResponseEntity<EntityModel<Categoria>> atualizarProdutosDaCategoria(@PathVariable("id") Integer idCategoria,
            List<Produto> produtos) {
        Categoria categoria = categoriaService.atualizarProdutosDaCategoria(idCategoria, produtos);

        EntityModel<Categoria> entityModelCategoria = categoriaModelAssembler.toModel(categoria);

        return ResponseEntity.ok(entityModelCategoria);
    }
}

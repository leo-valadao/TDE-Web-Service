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

import com.leonardo.tde.domain.Produto;
import com.leonardo.tde.domain.model_assembler.ProdutoModelAssembler;
import com.leonardo.tde.domain.Categoria;
import com.leonardo.tde.service.ProdutoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API do Produto - Versão 1
@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoResource {

    // Objetos
    // Serviço do Produto
    @Autowired
    private ProdutoService produtoService;

    // Montador de Modelo do Produto
    // @Autowired
    private ProdutoModelAssembler produtoModelAssembler = new ProdutoModelAssembler();

    // API's
    // Obters Todos os Produtos
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Produto>>> obterTodosProdutos() {
        List<Produto> produtos = produtoService.todosProdutos();

        if (!produtos.isEmpty()) {
            List<EntityModel<Produto>> listaEntityModelProdutos = new ArrayList<EntityModel<Produto>>();

            listaEntityModelProdutos.addAll(produtos.stream().map(produtoModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Produto>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelProdutos);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Produto Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> obterProdutoPorId(@PathVariable("id") Integer id) {
        Produto produto = produtoService.produtoPorId(id);

        EntityModel<Produto> entityModelProduto = produtoModelAssembler.toModel(produto);

        return ResponseEntity.ok(entityModelProduto);
    }

    // Salvar a Produto
    @PostMapping
    public ResponseEntity<EntityModel<Produto>> salvarProduto(@RequestBody @Valid Produto produto,
            HttpServletRequest request) {
        produtoService.salvarProduto(produto);

        URI uri = linkTo(methodOn(ProdutoResource.class).obterProdutoPorId(produto.getId())).withSelfRel().toUri();
        EntityModel<Produto> entityModelProduto = produtoModelAssembler.toModel(produto);

        return ResponseEntity.created(uri).body(entityModelProduto);
    }

    // Atualizar a Produto
    @PutMapping
    public ResponseEntity<EntityModel<Produto>> atualizarProduto(@RequestBody Produto produto) {
        produtoService.produtoPorId(produto.getId());
        produtoService.atualizarProduto(produto);

        EntityModel<Produto> entityModelProduto = produtoModelAssembler.toModel(produto);

        return ResponseEntity.ok(entityModelProduto);
    }

    // Excluir a Produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable("id") Integer id) {
        produtoService.excluirProduto(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar os Categorias da Produto
    @PatchMapping("/{id}/categorias")
    public ResponseEntity<EntityModel<Produto>> atualizarCategoriasDoProduto(@PathVariable("id") Integer idProduto,
            List<Categoria> categorias) {
        Produto produto = produtoService.atualizarCategoriasDoProduto(idProduto, categorias);

        EntityModel<Produto> entityModelProduto = produtoModelAssembler.toModel(produto);

        return ResponseEntity.ok(entityModelProduto);
    }
}

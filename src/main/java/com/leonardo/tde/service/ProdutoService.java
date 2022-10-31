package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.Categoria;
import com.leonardo.tde.domain.Produto;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.ProdutoRepository;

@Service
public class ProdutoService {

    // Objetos
    // Repositório do Produto
    @Autowired
    private ProdutoRepository produtoRepository;

    // Serviço da Categoria
    @Autowired
    private CategoriaService categoriaService;

    // Métodos
    // Buscar Todos os Produtos
    public List<Produto> todosProdutos() {
        return produtoRepository.findAll();
    }

    // Buscar Produto Por ID
    public Produto produtoPorId(int id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto Não Encontrado! ID: " + id));
    }

    // Salvar Produto
    public void salvarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    // Atualizar Produto
    public void atualizarProduto(Produto produto) {
        if (produtoRepository.existsById(produto.getId())) {
            produtoRepository.saveAndFlush(produto);
        } else {
            throw new NotFoundException("Produto Não Encontrado! ID: " + produto.getId());
        }
    }

    // Excluir Produto Por ID
    public void excluirProduto(Integer id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Produto Não Encontrado! ID: " + id);
        }
    }

    // Atualizar Categorias do Produto
    public Produto atualizarCategoriasDoProduto(Integer id, List<Categoria> categorias) {
        if (produtoRepository.existsById(id)) {
            Produto produto = produtoRepository.findById(id).get();
            List<Categoria> oldCategorias = produto.getCategorias();

            produto.setCategorias(categorias);
            this.salvarProduto(produto);

            if (oldCategorias != null) {
                for (Categoria categoria : oldCategorias) {
                    this.categoriaService.excluirCategoria(categoria.getId());
                }
            }

            return produto;
        } else {
            throw new NotFoundException("Produto Não Encontrado! ID: " + id);
        }
    }
}

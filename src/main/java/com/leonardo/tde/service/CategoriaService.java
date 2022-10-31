package com.leonardo.tde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.domain.Categoria;
import com.leonardo.tde.domain.Produto;
import com.leonardo.tde.error.NotFoundException;
import com.leonardo.tde.repository.CategoriaRepository;

@Service
public class CategoriaService {

    // Objetos
    // Repositório do Categoria
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Serviço do Produto
    // @Autowired
    // private ProdutoService produtoService;

    // Métodos
    // Buscar Todas os Categorias
    public List<Categoria> todasCategorias() {
        return categoriaRepository.findAll();
    }

    // Buscar Categoria Por ID
    public Categoria categoriaPorId(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria Não Encontrada! ID: " + id));
    }

    // Salvar Categoria
    public void salvarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    // Atualizar Categoria
    public void atualizarCategoria(Categoria categoria) {
        if (categoriaRepository.existsById(categoria.getId())) {
            categoriaRepository.saveAndFlush(categoria);
        } else {
            throw new NotFoundException("Categoria Não Encontrada! ID: " + categoria.getId());
        }
    }

    // Excluir Categoria Por ID
    public void excluirCategoria(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new NotFoundException("Categoria Não Encontrada! ID: " + id);
        }
    }

    // Atualizar Produtos da Categoria
    // public Categoria atualizarProdutosDoCategorias(Integer id, List<Produto> produtos) {
    //     if (categoriaRepository.existsById(id)) {
    //         Categoria categoria = categoriaRepository.findById(id).get();
    //         List<Produto> oldProdutos = categoria.getProdutos();

    //         categoria.setProdutos(produtos);
    //         this.salvarCategoria(categoria);

    //         if (oldProdutos != null) {
    //             for (Produto produto : oldProdutos) {
    //                 this.produtoService.excluirProduto(produto.getId());
    //             }
    //         }

    //         return categoria;
    //     } else {
    //         throw new NotFoundException("Categoria Não Encontrada! ID: " + id);
    //     }
    // }
}

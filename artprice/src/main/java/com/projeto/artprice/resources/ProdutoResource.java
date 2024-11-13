package com.projeto.artprice.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.model.Produto;
import com.projeto.artprice.service.ProdutoService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping(value = "/cadastrarProduto")
    public Produto cadastrarProduto(@RequestBody Produto p) {
        return produtoService.cadastrarProduto(p);
    }

    @GetMapping(value = "/listarProdutos")
    public List<Produto> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return produtos;
    }

    @DeleteMapping(value = "deletarProduto/{id}")
    public void deletarProduto (@PathVariable Long id){
        produtoService.excluirProduto(id);
    }

    @PutMapping("alterarProduto/{id}")
    public Boolean alterarPrdoduto(@PathVariable Long id, @RequestBody Produto p) {
        return produtoService.alterarProduto(id, p);
    }

    @GetMapping("/verProduto/{id}")
    public Produto verProduto(@PathVariable Long id) {
        return produtoService.mostrarProduto(id).get();
    }
    
    
    
    
}

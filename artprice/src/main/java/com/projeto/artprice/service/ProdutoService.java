package com.projeto.artprice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.model.Produto;
import com.projeto.artprice.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrarProduto (Produto p){
        return produtoRepository.save(p);
    }

    public void excluirProduto (Long id){
        produtoRepository.deleteById(id);
    }

    public Optional<Produto> verProduto (Long id){
        return produtoRepository.findById(id);
    }

    public Boolean alterarProduto (Long id, Produto p){
        Optional<Produto> produtoOptional = verProduto(id);
        if(produtoOptional.isPresent() && !produtoOptional.isEmpty()){
            Produto novo = produtoOptional.get();
            novo.setNome(p.getNome());
            novo.setValor(p.getValor());
            produtoRepository.save(novo);
            return true;
        }
        return false;
    }

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> mostrarProduto(Long id){
        return produtoRepository.findById(id);
    }
}

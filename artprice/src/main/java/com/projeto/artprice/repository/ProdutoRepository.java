package com.projeto.artprice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.artprice.model.Produto;



public interface ProdutoRepository extends JpaRepository<Produto, Long>{
 
}
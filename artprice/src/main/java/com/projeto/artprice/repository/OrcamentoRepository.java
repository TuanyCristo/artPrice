package com.projeto.artprice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.artprice.model.Artesao;
import com.projeto.artprice.model.Cliente;
import com.projeto.artprice.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    List<Orcamento> findByCliente(Cliente cliente);

    List<Orcamento> findByArtesao(Artesao artesao);
}

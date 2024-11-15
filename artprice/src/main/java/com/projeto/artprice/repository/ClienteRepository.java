package com.projeto.artprice.repository;

import com.projeto.artprice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository  extends JpaRepository <Cliente, Long>{
    Cliente findByTelefone(String telefone);
}

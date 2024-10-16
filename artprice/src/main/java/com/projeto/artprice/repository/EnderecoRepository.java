package com.projeto.artprice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Cep;


public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Endereco findByCep(Cep cep);
}

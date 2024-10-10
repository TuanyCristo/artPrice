package com.projeto.artprice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.artprice.model.Cep;

public interface CepRepository extends JpaRepository<Cep, Integer> {

    Cep findByCep(String cep);
<<<<<<< HEAD
    
=======
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
    
}


package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.repository.CepRepository;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public Cep cadastrarCep(Cep cep){
        return cepRepository.save(cep);
    }

}

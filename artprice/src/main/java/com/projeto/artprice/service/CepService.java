package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.repository.CepRepository;

@Service
public class CepService {   
    @Autowired
    private CepRepository cepRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Cep buscarCep (Cep cep){
        return cepRepository.findByCep(cep.getCep());
    }

    // public Cep buscarCepApi (String cep){

    // }


}

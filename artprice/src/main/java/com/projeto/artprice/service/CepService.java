package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
<<<<<<< HEAD
import com.projeto.artprice.dto.CepDTO;
=======
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.repository.CepRepository;

@Service
<<<<<<< HEAD
public class CepService {

    private static final String VIA_CEP_API_URL = "https://viacep.com.br/ws/{cep}/json/";

    @Autowired
    private RestTemplate restTemplate; //comunicação com a api externa

    @Autowired
    private CepRepository cepRepository;

    
    /**
     * Método para consultar o cep no banco
     * @param cep
     */
    public Cep consultarCep(String cep) {
        return cepRepository.findByCep(cep);
    }

    /**
     * Método para consultar a API externa (ViaCEP).
     * @param cep
     */
    public CepDTO consultarCepApi(String cep) {
        String url = VIA_CEP_API_URL.replace("{cep}", cep);
        return restTemplate.getForObject(url, CepDTO.class);
    }

    public Cep cadastrarCep(Cep cep){
        return cepRepository.save(cep);
=======
public class CepService {   
    @Autowired
    private CepRepository cepRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Cep buscarCep (Cep cep){
        return cepRepository.findByCep(cep.getCep());
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
    }

    // public Cep buscarCepApi (String cep){

    // }


}

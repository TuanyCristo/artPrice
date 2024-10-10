package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.projeto.artprice.dto.CepDTO;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.repository.CepRepository;

@Service
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
    }

}

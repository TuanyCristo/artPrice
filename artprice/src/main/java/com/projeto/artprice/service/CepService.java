
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

    public Cep cadastrarCep(Cep cep){
        Cep novoCep = buscaCep(cep.getCep());
        if(novoCep == null){
            return cepRepository.save(tratarCep(buscaCepApi(cep.getCep())));
        }
        return novoCep;

    }     

    /**
     * Método criado para tratar o cepDTO e devolver um CEP
     * @param cepDTO
     * @return
     */
    private Cep tratarCep(CepDTO cepDTO) {
        Cep novoCep = new Cep();

        novoCep.setCep(cepDTO.getCep());
        novoCep.setCidade(cepDTO.getLocalidade());
        novoCep.setEstado(cepDTO.getUf());


        return novoCep;
    }

    /**
     * Método para consultar a API externa (ViaCEP).
     * @param cep
     */
    public CepDTO buscaCepApi(String cep) {
        String url = VIA_CEP_API_URL.replace("{cep}", cep);
        return restTemplate.getForObject(url, CepDTO.class);
    }
    
    public Cep buscaCep (String cep){
        Cep novoCep = cepRepository.findByCep(cep);
        if (novoCep == null) {
            CepDTO buscaCep = buscaCepApi(cep);
            novoCep = tratarCep(buscaCep);
            return novoCep;
        }
        return novoCep;
    }






}

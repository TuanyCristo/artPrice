
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

    public Cep cadastrarCep(Cep cep) {
        return cepRepository.save(cep);
    }

    public Cep buscaCep (String cep){
        Cep novoCep = cepRepository.findByCep(cep);
        if (novoCep != null) {
            return novoCep;
        }
        return buscarCepAPI(cep);
    }

    /**
     * Método para consultar a API externa (ViaCEP) e tratar o retorno.
     * @param cep
     * @return
     */
    public Cep buscarCepAPI(String cep) {
        CepDTO cepDTO = consultaCepApi(cep);
        return tratarCep(cepDTO);
    }

    /**
     * Método criado para tratar o cepDTO e devolver um CEP
     * @param cepDTO
     * @return
     */
    private Cep tratarCep(CepDTO cepDTO) {
        if (cepDTO == null) {
            throw new IllegalArgumentException("CEP não encontrado na API.");
        }
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
    private CepDTO consultaCepApi(String cep) {
        if (cep == null || cep.isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou vazio.");
        }
        String url = VIA_CEP_API_URL.replace("{cep}", cep);
        return restTemplate.getForObject(url, CepDTO.class);
    }
    
}

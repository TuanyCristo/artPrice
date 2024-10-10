package com.projeto.artprice.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.dto.CepDTO;
import com.projeto.artprice.service.CepService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EnderecoResource {

    @Autowired
    private CepService cepService;

    // Logger para o controlador
    private static final Logger logger = LoggerFactory.getLogger(EnderecoResource.class);

    @GetMapping("/api/endereco/{cep}")
    public Endereco getEndereco(@PathVariable String cep) {
        logger.info("Iniciando busca de endereço para o CEP: {}", cep);
    
        // Primeiro tenta encontrar o CEP no banco de dados
        Cep cepObj1 = cepService.consultarCep(cep);
        Endereco endereco = new Endereco();
        if(cepObj1 == null){
            CepDTO cepApi = cepService.consultarCepApi(cep);
                if (cepApi == null) {
                throw new RuntimeException("CEP não encontrado.");
            } else {
                Cep cepObj = new Cep();
                cepObj.setBairro(cepApi.getBairro());
                cepObj.setCep(cepApi.getCep());
                cepObj.setCidade(cepApi.getLocalidade());
                cepObj.setEstado(cepApi.getUf());
                
                Cep cepCadastrado = cepService.cadastrarCep(cepObj);
        
                endereco.setCep(cepCadastrado);  // Agora é garantido que o cepObj não é null
            
                return endereco;
            }   
        }
        endereco.setCep(cepObj1);
        return endereco;

    }
   
    
}

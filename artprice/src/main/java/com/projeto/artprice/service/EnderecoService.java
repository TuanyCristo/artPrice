package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.artprice.dto.CepDTO;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CepService cepService;

    public Endereco cadastrarEndereco (Cep cep, String logradouro, String numero, String bairro, String complemento){
        Endereco end = new Endereco();
        Cep novoCep = cepService.buscaCep(cep.getCep());
        end.setCep(novoCep);
        CepDTO infosCep = cepService.buscaCepApi(cep.getCep());

        if(infosCep.getLogradouro() == null){
            end.setLogradouro(logradouro);
        } else {
            end.setLogradouro(infosCep.getLogradouro());
        }

        if(infosCep.getBairro() == null){
            end.setBairro(bairro);
        } else {
            end.setBairro(infosCep.getBairro());
        }

        end.setNumero(numero);

        end.setComplemento(complemento);
        
        
        return enderecoRepository.save(end);
    }


}

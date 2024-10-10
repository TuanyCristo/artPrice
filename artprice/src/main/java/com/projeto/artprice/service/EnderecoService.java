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
    private CepService cepService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco cadastrarEnd(Endereco end){
        return enderecoRepository.save(end);
    }

    /**
     * Método que verifica o endereço e retorna ele com os dados atualizados, 
     * mas sem salvar no banco.
     */
    public Endereco retornaEndereco(Endereco end){
        //busca cep no banco
        Cep cepExistente = cepService.consultarCep(end.getCep().getCep());
        if(cepExistente != null){
            end.setCep(cepExistente);
        } else {
            //busca cep na api
            CepDTO buscaApi = cepService.consultarCepApi(end.getCep().getCep());

            //setta os dados da api
            Cep novoCep = new Cep();
            novoCep.setCep(buscaApi.getCep());
            novoCep.setCidade(buscaApi.getLocalidade());
            novoCep.setEstado(buscaApi.getUf());
            novoCep.setLogradouro(buscaApi.getLocalidade());
            novoCep.setBairro(buscaApi.getBairro());

            end.setCep(novoCep);
        }
        return end;
    }
}

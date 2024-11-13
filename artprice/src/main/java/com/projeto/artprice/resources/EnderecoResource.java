package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.service.CepService;
import com.projeto.artprice.service.EnderecoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/end-completo")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CepService cepService;

    @PostMapping("/cadastrarEnd")
    public ResponseEntity<?> setEndereco(@RequestBody Endereco end) {
        Cep novoCep = cepService.buscaCep(end.getCep().getCep());
        if(novoCep == null){
            enderecoService.cadastrarEndereco(end);
            return ResponseEntity.ok().body(end);
        }

        end.setCep(novoCep);
        enderecoService.cadastrarEndereco(end);
        return ResponseEntity.ok().body(end);
    }
    

   
    
}

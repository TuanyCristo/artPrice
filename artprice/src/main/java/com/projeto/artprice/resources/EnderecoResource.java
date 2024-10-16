package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.service.EnderecoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/end-completo")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastrarEnd")
    public ResponseEntity<?> setEndereco(@RequestBody Endereco end) {
        Endereco endereco = enderecoService.cadastrarEndereco(end.getCep(), end.getLogradouro(), end.getNumero(), end.getBairro(), end.getComplemento());
        return ResponseEntity.ok().body(endereco);
    }
    

   
    
}

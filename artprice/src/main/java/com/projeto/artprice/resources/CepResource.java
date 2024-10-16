package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.service.CepService;

@RestController
@RequestMapping(value = "/cep")
public class CepResource {

    @Autowired
    private CepService cepService;

    @PostMapping(value = "/cadastroCep")
    public ResponseEntity<?> cadastrarCEP(@RequestBody Cep cep) {
        try {
            Cep novoCep = cepService.buscaCep(cep.getCep());
            if(novoCep != null){
                cep.setCidade(novoCep.getCidade());
                cep.setEstado(novoCep.getEstado());
                cepService.cadastrarCep(cep);
            }
            return ResponseEntity.ok().body(cep);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar CEP: " + e.getMessage());
        }
        
    }

    @GetMapping("/listar/{cep}")
    public ResponseEntity<?> mostrarCep(@PathVariable String cep) { // PathVariable para usar parametro de caminho
        Cep novoCep = cepService.buscaCep(cep);

            if (novoCep == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CEP não encontrado");
            }

        return ResponseEntity.ok(novoCep);
    }


}

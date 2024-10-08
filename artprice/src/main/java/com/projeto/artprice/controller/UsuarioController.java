package com.projeto.artprice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.service.CepService;
import com.projeto.artprice.service.EnderecoService;
import com.projeto.artprice.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;

        @Autowired
    private CepService cepService;

    @PostMapping("/cadastrar")
    try {
            Cep cep = cepService.buscarCepPorCodigo(usuario.getEndereco().getCepId().getCep());
            if (cep == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // Caso o CEP não seja encontrado
            }

            // Configurar o Endereco
            Endereco endereco = usuario.getEndereco();
            endereco.setCepId(cep);
            
            // Salvar o Endereco
            endereco = enderecoService.salvarEndereco(endereco);

            // Associar o Endereco ao Usuario
            usuario.setEndereco(endereco);

            // Salvar o Usuario
            Usuario usuarioSalvo = usuarioService.cadastrarUsuario(usuario);

            return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

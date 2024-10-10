package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.service.EnderecoService;
import com.projeto.artprice.service.UsuarioService;


@RestController
@RequestMapping(value = "/api")
public class UsuarioResource {
	@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;
    /**
     * Metodo que cria o usuário, primeiro ele verifica se o endereco e 
     * o cep nao estao nulos e depois ele setta novamente(talvez eu esteja sendo
     * redundante, opinem) para garantir que está usando o cep correto.
     * Em seguida ele cria o usuario.
     * Pq precisamos fazer assim? Professor pediu pra relacionar a tabela cep
     * e a tabela endereço.
     * @param usuario
     */
    @PostMapping(value = "/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
    
        //verifica se o usuario ja esta cadastrado
        if(!usuarioService.isEmailCadastrado(usuario.getEmail())){
            return ResponseEntity.badRequest().body("E-mail já está em uso");
        }

        usuarioService.cadastrarUsuario(usuario);

        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/salvarEndereco")
    public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoSalvo = enderecoService.cadastrarEnd(endereco);
        return ResponseEntity.ok().body(enderecoSalvo);
    }

}


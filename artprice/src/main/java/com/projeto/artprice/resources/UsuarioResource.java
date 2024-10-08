package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.service.CepService;
import com.projeto.artprice.service.EnderecoService;
import com.projeto.artprice.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	@Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private CepService cepService;

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
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        // Verifica Endereço nulo
        Endereco endereco = usuario.getEndereco();
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
    
        // Verifique CEP nulo
        Cep cep = endereco.getCep();
        if (cep == null) {
            throw new IllegalArgumentException("CEP não pode ser nulo");
        }
    
        // Salva Cep
        Cep cepSalvo = cepService.cadastrarCep(cep);
        endereco.setCep(cepSalvo);
    
        // Salva Endereço
        Endereco enderecoSalvo = enderecoService.salvarEndereco(endereco);
        usuario.setEndereco(enderecoSalvo);
    
        // Salva Usuario
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.ok().body(novoUsuario);
    }
}


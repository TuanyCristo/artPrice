package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.service.LoginServiceImp;

@RestController
@RequestMapping(value = "/api")
public class LoginResource {
    @Autowired
    private LoginServiceImp loginServiceImp;

    /**
     * Metodo que autentica o email e senha
     * @param loginDTO
     * @return Usuario
     */
    @PostMapping(value = "/login")
    public ResponseEntity<Usuario> autenticar(@RequestBody LoginDTO loginDTO) {
        // Verifica DTO está nulo
        String email = loginDTO.getEmail();
        if (email == null) {
            throw new IllegalArgumentException("E-mail não pode ser nulo");
        }
    
        // Verifique senha é  nulo
        String senha = loginDTO.getSenha();
        if (senha == null) {
            throw new IllegalArgumentException("Senha não pode ser nulo");
        }
     
        // Retorna usuário
        Usuario novoUsuario = loginServiceImp.login(loginDTO);
        return ResponseEntity.ok().body(novoUsuario);
    }

}

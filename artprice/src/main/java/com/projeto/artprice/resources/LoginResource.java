package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.service.LoginService;

@RestController
@RequestMapping(value = "/api")
public class LoginResource {
    @Autowired
    private LoginService loginService;

    /**
     * Metodo que autentica o email e senha
     * @param loginDTO
     * @return Usuario
     */
    @PostMapping(value = "/login")
    public ResponseEntity<Usuario> autenticar(@RequestBody LoginDTO loginDTO) {
        // Verifica DTO está nulo
        String email = loginDTO.getEmail();
        String senha = loginDTO.getSenha();
        if (email == null || senha == null) {
            throw new IllegalArgumentException("E-mail e senha não podem ser nulos");
        }
     
        Usuario novoUsuario = loginService.login(loginDTO);
        if(novoUsuario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(novoUsuario);
    }

}

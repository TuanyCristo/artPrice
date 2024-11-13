package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.service.LoginService;

@RestController
@RequestMapping(value = "/api")
public class LoginResource {
    @Autowired
    private LoginService loginService;

    /**
     * Metodo que autentica o email e senha
     * @param loginDTO
     * @return Boolean
     */
    @PostMapping(value = "/login")
    public Long autenticar(@RequestBody LoginDTO loginDTO) {

        // Verifica DTO está nulo
        String email = loginDTO.getEmail();
        String senha = loginDTO.getSenha();
        if (email == null || senha == null) {
            throw new IllegalArgumentException("E-mail e senha não podem ser nulos");
        }
     
        Long id = loginService.login(loginDTO);
        if(id > 0){
            return id;
        }

        return -1L;
    }

}

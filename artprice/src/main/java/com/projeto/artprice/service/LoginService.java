package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.repository.UsuarioRepository;

@Service
public class LoginService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean login(LoginDTO loginDTO) {
        return usuarioRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha()) != null;
    }

    
}

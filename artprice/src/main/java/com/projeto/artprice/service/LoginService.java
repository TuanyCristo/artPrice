package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.repository.UsuarioRepository;

@Service
public class LoginService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(LoginDTO loginDTO) {
        
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail());
        try {
        if (usuario != null && usuario.getSenha().equals(loginDTO.getSenha())) {
            return usuario;
        }
        } catch (Exception e) {
        }
        return null;
    }

    
}

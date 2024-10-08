package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.repository.UsuarioRepository;

public class LoginServiceImp implements LoginService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario login(LoginDTO loginDTO) {
        
        Usuario usuario = usuarioRepository.buscaPorEmail(loginDTO.getEmail());
        try {
        if (usuario != null && usuario.getSenha().equals(loginDTO.getSenha())) {
            return usuario;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
}

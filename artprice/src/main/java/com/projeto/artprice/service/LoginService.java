package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.model.Artesao;
import com.projeto.artprice.repository.ArtesaoRepository;

@Service
public class LoginService{

    @Autowired
    private ArtesaoRepository usuarioRepository;

    public Long login(LoginDTO loginDTO) {
        Artesao usuario = usuarioRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
        return usuario != null ? usuario.getId() : -1L;
    }

    
}

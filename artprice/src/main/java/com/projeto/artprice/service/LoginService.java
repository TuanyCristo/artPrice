package com.projeto.artprice.service;

import org.springframework.stereotype.Service;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.model.Usuario;

@Service
public interface LoginService {
    Usuario login(LoginDTO loginDTO);
}

package com.projeto.artprice.service;

import com.projeto.artprice.dto.LoginDTO;
import com.projeto.artprice.model.Usuario;

public interface LoginService {
    Usuario login(LoginDTO loginDTO);
}

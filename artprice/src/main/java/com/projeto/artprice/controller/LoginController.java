package com.projeto.artprice.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.artprice.service.UsuarioService;

public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioService usuarioService;
    
}

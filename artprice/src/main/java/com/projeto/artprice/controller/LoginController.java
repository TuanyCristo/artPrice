package com.projeto.artprice.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.service.UsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioService usuarioService;
    
}

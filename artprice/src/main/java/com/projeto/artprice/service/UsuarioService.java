package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.artprice.repository.UsuarioRepository;
import com.projeto.artprice.model.Usuario;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioResository;

    public Usuario cadastrarUsuario(Usuario u){
        return usuarioResository.save(u);
    }
}

package com.projeto.artprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.repository.UsuarioRepository;



@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioResository;

    public Usuario cadastrarUsuario(Usuario u){
        return usuarioResository.save(u);
    }

    public void deletarUsuario(Usuario u){
        usuarioResository.deleteById(u.getId());
    }

    public Usuario alterarUsuario(Usuario u, Usuario alter){
        Usuario usuarioAlterado = u;
        if(alter.getEmail() != null){
            usuarioAlterado.setEmail(alter.getEmail());
        } else if (alter.getNome() != null) {
            usuarioAlterado.setNome(alter.getNome());
        } else if (alter.getEndereco() != null){
            if(alter.getEndereco().getCep() != null){
                usuarioAlterado.setEndereco(alter.getEndereco());
                usuarioAlterado.getEndereco().setCep(alter.getEndereco().getCep());
            } else {
                usuarioAlterado.setEndereco(alter.getEndereco());
            }
        } else if (alter.getSenha() != null){
            usuarioAlterado.setSenha(alter.getSenha());
        } else if (alter.getTelefone() != null){
            usuarioAlterado.setTelefone(alter.getTelefone());
        } else {
            return u;
        }
        return usuarioAlterado;

    }

    public Usuario buscaPorEmail(String email){
        return usuarioResository.findByEmail(email);
    }
}

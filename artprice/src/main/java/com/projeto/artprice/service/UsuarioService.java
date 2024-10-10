package com.projeto.artprice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.repository.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioResository;

    @Autowired
    private EnderecoService enderecoService;

    public Usuario cadastrarUsuario(Usuario u){
        Endereco enderecoSalvo = enderecoService.cadastrarEnd(u.getEndereco());
        u.setEndereco(enderecoSalvo);
        return usuarioResository.save(u);
    }

    public void deletarUsuario(Usuario u){
        usuarioResository.deleteById(u.getId());
    }

    public boolean isEmailCadastrado(String email){
        Usuario user = usuarioResository.findByEmail(email);
        return user == null;
    }

    public List<Usuario> listarTodos() {
        return usuarioResository.findAll();
    }

}

    // public Usuario alterarUsuario(Usuario u, Usuario alter){
    //     Usuario usuarioAlterado = u;
    //     if(alter.getEmail() != null){
    //         usuarioAlterado.setEmail(alter.getEmail());
    //     } else if (alter.getNome() != null) {
    //         usuarioAlterado.setNome(alter.getNome());
    //     } else if (alter.getEndereco() != null){
    //         if(alter.getEndereco().getCep_id() != null){
    //             usuarioAlterado.setEndereco(alter.getEndereco());
    //             usuarioAlterado.getEndereco().setCep_id(alter.getEndereco().getCep_id());
    //         } else {
    //             usuarioAlterado.setEndereco(alter.getEndereco());
    //         }
    //     } else if (alter.getSenha() != null){
    //         usuarioAlterado.setSenha(alter.getSenha());
    //     } else if (alter.getTelefone() != null){
    //         usuarioAlterado.setTelefone(alter.getTelefone());
    //     } else {
    //         return u;
    //     }
    //     return usuarioAlterado;

    // }
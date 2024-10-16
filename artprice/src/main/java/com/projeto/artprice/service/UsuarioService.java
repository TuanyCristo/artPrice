package com.projeto.artprice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.repository.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioResository;

    @Autowired
    private CepService cepService;

    @Autowired
    private EnderecoService enderecoService;

    public Usuario cadastrarUsuario(Usuario u){
        //busca e cadastra cep se necessário
        Cep novoCep = u.getEndereco().getCep();

        novoCep = cepService.cadastrarCep(novoCep);

        //cadastra o endereço
        Endereco novoEndereco = enderecoService.cadastrarEndereco(novoCep, u.getEndereco().getLogradouro(),
                                u.getEndereco().getNumero(),
                                u.getEndereco().getBairro(),
                                u.getEndereco().getComplemento());

        u.setEndereco(novoEndereco);


        return usuarioResository.save(u);
    }

    public void deletarUsuario(Usuario u){
        usuarioResository.deleteById(u.getId());
    }

    public boolean isEmailCadastrado(String email){
        Usuario user = usuarioResository.findByEmail(email);
        return user != null;
    }

    public List<Usuario> listarTodos() {
        return usuarioResository.findAll();
    }

}
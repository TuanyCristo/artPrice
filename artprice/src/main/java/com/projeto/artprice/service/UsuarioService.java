package com.projeto.artprice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.artprice.dto.UsuarioDTO;
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

    public Usuario cadastrarUsuario(UsuarioDTO u){
        //cria o usuário
        Usuario user = new Usuario();
        user.setNome(u.getNome());
        user.setEmail(u.getEmail());
        user.setSenha(u.getSenha());
        user.setTelefone(u.getTelefone());

        //busca e cadastra cep se necessário
        Cep novoCep = cepService.buscaCep(u.getCep());
    
        //cadastra o endereço
        Endereco novoEndereco = enderecoService.cadastrarEndereco(novoCep, u.getLogradouro(),
                                u.getNumero(), u.getBairro(), u.getComplemento());

        //setta informações no usuário
        user.setEndereco(novoEndereco);

        return usuarioResository.save(user);
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
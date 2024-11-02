package com.projeto.artprice.service;

import java.util.List;
import java.util.Optional;

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

    public UsuarioDTO cadastrarUsuario(UsuarioDTO u) {
        // cria o usuário
        Usuario user = new Usuario();
        user.setNome(u.getNome());
        user.setEmail(u.getEmail());
        user.setSenha(u.getSenha());
        user.setTelefone(u.getTelefone());
    
        //busca o cep
        Cep novoCep = cepService.buscaCep(u.getCep());
        if (novoCep == null) {
            novoCep = cepService.buscarCepAPI(u.getCep());
            if (novoCep == null) {
                throw new IllegalArgumentException("CEP inválido");
            }
            novoCep = cepService.cadastrarCep(novoCep);
        }
    
        // Cadastra o endereço
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep(novoCep);
        novoEndereco.setLogradouro(u.getLogradouro());
        novoEndereco.setBairro(u.getBairro());
        novoEndereco.setNumero(u.getNumero());
        novoEndereco.setComplemento(u.getComplemento());

        novoEndereco = enderecoService.cadastrarEndereco(novoEndereco);
    
        // Associa o Endereco ao Usuario
        user.setEndereco(novoEndereco);
        usuarioResository.save(user);
        Usuario cadastrado = UsuarioDTO(user);
    
        return cadastrado;
    }


    public void deletarUsuario(Long id){
        usuarioResository.deleteById(id);
    }

    public boolean isEmailCadastrado(String email){
        Usuario user = usuarioResository.findByEmail(email);
        return user != null;
    }

    public List<Usuario> listarTodos() {
        return usuarioResository.findAll();
    }

    public Optional<Usuario> buscarId(Long id){
        return usuarioResository.findById(id);
    }

}
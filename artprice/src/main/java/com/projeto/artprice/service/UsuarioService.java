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
        Usuario cadastrado = usuarioResository.save(user);

        return new UsuarioDTO(cadastrado);
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

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        
        Usuario user = buscarId(id).get();

        if(usuarioDTO.getNome() != null){
            user.setNome(usuarioDTO.getNome());
        }
        if(usuarioDTO.getEmail() != null){
            user.setEmail(usuarioDTO.getEmail());
        }
        if(usuarioDTO.getSenha() != null){
            user.setSenha(usuarioDTO.getSenha());
        }
        if(usuarioDTO.getTelefone() != null){
            user.setTelefone(usuarioDTO.getTelefone());
        }
        if(usuarioDTO.getCep() != null || usuarioDTO.getBairro() != null || usuarioDTO.getCidade() != null 
            ||usuarioDTO.getEstado() != null || usuarioDTO.getComplemento() != null
            || usuarioDTO.getLogradouro() != null || usuarioDTO.getNumero() != null){
            
            Cep novoCep = cepService.buscaCep(usuarioDTO.getCep());

            if(novoCep == null){
                novoCep = cepService.buscarCepAPI(usuarioDTO.getCep());
            } else {
                novoCep.setCep(cepService.buscaCep(usuarioDTO.getCep()).getCep());
                novoCep.setCidade(usuarioDTO.getCidade());
                novoCep.setEstado(usuarioDTO.getEstado());
                cepService.cadastrarCep(novoCep);
            }

            Endereco novo = new Endereco();
                novo.setBairro(usuarioDTO.getBairro());
                novo.setCep(novoCep);
                novo.setComplemento(usuarioDTO.getComplemento());
                novo.setLogradouro(usuarioDTO.getLogradouro());
                novo.setNumero(usuarioDTO.getNumero());

                enderecoService.cadastrarEndereco(novo);
        }

        usuarioResository.save(user);


        return new UsuarioDTO(user);
        
    }

}
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
        
        if(user != null){
            UsuarioDTO usuarioAtual = new UsuarioDTO(user);
            if(!usuarioAtual.getNome().equalsIgnoreCase(usuarioDTO.getNome())){
                user.setNome(usuarioDTO.getNome());
            }
            if(!usuarioAtual.getEmail().equalsIgnoreCase(usuarioDTO.getEmail())){
                user.setEmail(usuarioDTO.getEmail());
            }
            if(!usuarioAtual.getSenha().equalsIgnoreCase(usuarioDTO.getSenha())){
                user.setSenha(usuarioDTO.getEmail());
            }
            if(!usuarioAtual.getTelefone().equalsIgnoreCase(usuarioDTO.getTelefone())){
                user.setTelefone(usuarioDTO.getTelefone());
            }
            if(!usuarioAtual.getCep().equalsIgnoreCase(usuarioDTO.getCep())){
                if(cepService.buscaCep(usuarioDTO.getCep()) != null){
                    user.getEndereco().getCep().setCep(usuarioDTO.getCep());
                }
                if(!usuarioAtual.getCidade().equalsIgnoreCase(usuarioDTO.getCidade())){
                    user.getEndereco().getCep().setCep(usuarioDTO.getCidade());
                }
                if(!usuarioAtual.getEstado().equalsIgnoreCase(usuarioDTO.getEstado())){
                    user.getEndereco().getCep().setCep(usuarioDTO.getEstado());
                }
                cepService.cadastrarCep(user.getEndereco().getCep());
            }

            if(!usuarioAtual.getLogradouro().equalsIgnoreCase(usuarioDTO.getLogradouro())){
                user.getEndereco().setLogradouro(usuarioDTO.getLogradouro());
            }
            if(!usuarioAtual.getNumero().equalsIgnoreCase(usuarioDTO.getNumero())){
                user.getEndereco().setNumero(usuarioDTO.getNumero());
            }
            if(!usuarioAtual.getComplemento().equalsIgnoreCase(usuarioDTO.getComplemento())){
                user.getEndereco().setComplemento(usuarioDTO.getComplemento());
            }
            if(!usuarioAtual.getBairro().equalsIgnoreCase(usuarioDTO.getBairro())){
                user.getEndereco().setBairro(usuarioDTO.getBairro());
            }

            enderecoService.cadastrarEndereco(user.getEndereco());
            
            usuarioResository.save(user);

            return new UsuarioDTO(user);

        }

        return null;
        
    }

}
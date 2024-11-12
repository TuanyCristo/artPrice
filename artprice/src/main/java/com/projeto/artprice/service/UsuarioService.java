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
    private UsuarioRepository usuarioRepository;

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
        Usuario cadastrado = usuarioRepository.save(user);

        return new UsuarioDTO(cadastrado);
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public boolean isEmailCadastrado(String email){
        Usuario user = usuarioRepository.findByEmail(email);
        return user != null;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarId(Long id){
        return usuarioRepository.findById(id);
    }

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        
        if(usuarioOptional.isPresent()){
            Usuario user = usuarioOptional.get();

            if (usuarioDTO.getNome() != null && !usuarioDTO.getNome().isEmpty()) {
                user.setNome(usuarioDTO.getNome());
            }
            if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().isEmpty()) {
                user.setEmail(usuarioDTO.getEmail());
            }
            if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().isEmpty()) {
                user.setSenha(usuarioDTO.getSenha());
            }
            if (usuarioDTO.getTelefone() != null && !usuarioDTO.getTelefone().isEmpty()) {
                user.setTelefone(usuarioDTO.getTelefone());
            }

            if(usuarioDTO.getCep() != null && !usuarioDTO.getCep().isEmpty()){
                if(cepService.buscaCep(usuarioDTO.getCep()) != null){
                    user.getEndereco().getCep().setCep(usuarioDTO.getCep());
                    user.getEndereco().getCep().setCidade(usuarioDTO.getCidade());
                    user.getEndereco().getCep().setEstado(usuarioDTO.getEstado());
                    cepService.cadastrarCep(user.getEndereco().getCep());

                    if(usuarioDTO.getLogradouro() != null && !usuarioDTO.getLogradouro().isEmpty()){
                        user.getEndereco().setLogradouro(usuarioDTO.getLogradouro());
                    }
                    if(usuarioDTO.getNumero() != null && !usuarioDTO.getNumero().isEmpty()){
                        user.getEndereco().setNumero(usuarioDTO.getNumero());
                    }
                    if(usuarioDTO.getComplemento() != null && !usuarioDTO.getComplemento().isEmpty()){
                        user.getEndereco().setComplemento(usuarioDTO.getComplemento());
                    }
                    if(usuarioDTO.getBairro() != null && !usuarioDTO.getBairro().isEmpty()){
                        user.getEndereco().setBairro(usuarioDTO.getBairro());
                    }
                
                    enderecoService.cadastrarEndereco(user.getEndereco());
                    usuarioRepository.save(user);
                
                }
                return new UsuarioDTO(user);
          
            }  
        }

        return null;
    }

}
package com.projeto.artprice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.dto.ArtesaoDTO;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Artesao;
import com.projeto.artprice.repository.ArtesaoRepository;



@Service
public class ArtesaoService {

    @Autowired
    private ArtesaoRepository artesaoRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private EnderecoService enderecoService;

    public ArtesaoDTO cadastrarUsuario(ArtesaoDTO a) {
        // cria o usuário
        Artesao artesao = new Artesao();
        artesao.setNome(a.getNome());
        artesao.setEmail(a.getEmail());
        artesao.setSenha(a.getSenha());
        artesao.setTelefone(a.getTelefone());
    
        //busca o cep
        Cep novoCep = cepService.buscaCep(a.getCep());
        if (novoCep == null) {
            novoCep = cepService.buscarCepAPI(a.getCep());
            if (novoCep == null) {
                throw new IllegalArgumentException("CEP inválido");
            }
            novoCep = cepService.cadastrarCep(novoCep);
        }
    
        // Cadastra o endereço
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep(novoCep);
        novoEndereco.setLogradouro(a.getLogradouro());
        novoEndereco.setBairro(a.getBairro());
        novoEndereco.setNumero(a.getNumero());
        novoEndereco.setComplemento(a.getComplemento());

        novoEndereco = enderecoService.cadastrarEndereco(novoEndereco);
    
        // Associa o Endereco ao Usuario
        artesao.setEndereco(novoEndereco);
        Artesao cadastrado = artesaoRepository.save(artesao);

        return new ArtesaoDTO(cadastrado);
    }

    public void deletarUsuario(Long id){
        artesaoRepository.deleteById(id);
    }

    public boolean isEmailCadastrado(String email){
        Artesao artesao = artesaoRepository.findByEmail(email);
        return artesao != null;
    }

    public List<Artesao> listarTodos() {
        return artesaoRepository.findAll();
    }

    public Optional<Artesao> buscarId(Long id){
        return artesaoRepository.findById(id);
    }

    public ArtesaoDTO atualizarUsuario(Long id, ArtesaoDTO usuarioDTO) {
        Optional<Artesao> artesaoOptional = artesaoRepository.findById(id);
        
        if(artesaoOptional.isPresent()){
            Artesao artesao = artesaoOptional.get();

            if (usuarioDTO.getNome() != null && !usuarioDTO.getNome().isEmpty()) {
                artesao.setNome(usuarioDTO.getNome());
            }
            if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().isEmpty()) {
                artesao.setEmail(usuarioDTO.getEmail());
            }
            if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().isEmpty()) {
                artesao.setSenha(usuarioDTO.getSenha());
            }
            if (usuarioDTO.getTelefone() != null && !usuarioDTO.getTelefone().isEmpty()) {
                artesao.setTelefone(usuarioDTO.getTelefone());
            }

            if(usuarioDTO.getCep() != null && !usuarioDTO.getCep().isEmpty()){
                if(cepService.buscaCep(usuarioDTO.getCep()) != null){
                    artesao.getEndereco().getCep().setCep(usuarioDTO.getCep());
                    artesao.getEndereco().getCep().setCidade(usuarioDTO.getCidade());
                    artesao.getEndereco().getCep().setEstado(usuarioDTO.getEstado());
                    cepService.cadastrarCep(artesao.getEndereco().getCep());

                    if(usuarioDTO.getLogradouro() != null && !usuarioDTO.getLogradouro().isEmpty()){
                        artesao.getEndereco().setLogradouro(usuarioDTO.getLogradouro());
                    }
                    if(usuarioDTO.getNumero() != null && !usuarioDTO.getNumero().isEmpty()){
                        artesao.getEndereco().setNumero(usuarioDTO.getNumero());
                    }
                    if(usuarioDTO.getComplemento() != null && !usuarioDTO.getComplemento().isEmpty()){
                        artesao.getEndereco().setComplemento(usuarioDTO.getComplemento());
                    }
                    if(usuarioDTO.getBairro() != null && !usuarioDTO.getBairro().isEmpty()){
                        artesao.getEndereco().setBairro(usuarioDTO.getBairro());
                    }
                
                    enderecoService.cadastrarEndereco(artesao.getEndereco());
                    artesaoRepository.save(artesao);
                
                }
                return new ArtesaoDTO(artesao);
          
            }  
        }

        return null;
    }

}
package com.projeto.artprice.resources;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.dto.ArtesaoDTO;
import com.projeto.artprice.model.Artesao;
import com.projeto.artprice.service.ArtesaoService;

@RestController
@RequestMapping(value = "/usuario")
public class ArtesaoResource {

    @Autowired
    private ArtesaoService artesaoService;
    
    /**
     * Metodo que cria o usuário, primeiro ele verifica se o endereco e 
     * o cep nao estao nulos e depois ele setta novamente(talvez eu esteja sendo
     * redundante, opinem) para garantir que está usando o cep correto.
     * Em seguida ele cria o usuario.
     * Pq precisamos fazer assim? Professor pediu pra relacionar a tabela cep
     * e a tabela endereço.
     * @param usuario
     */
    @PostMapping(value = "/cadastro")
    public ArtesaoDTO cadastrarUsuario(@RequestBody ArtesaoDTO usuarioDTO) {
        return artesaoService.cadastrarUsuario(usuarioDTO);
    }

    @GetMapping(value = "/listar-usuarios")
    public List<ArtesaoDTO> listarUsuarios() {
        List<Artesao> usuarios = artesaoService.listarTodos();
        return usuarios.stream().map(usuario -> new ArtesaoDTO(usuario)).collect(Collectors.toList());
    }

    @GetMapping(value = "/verificarEmail/{email}")
    public Boolean verificarEmailCadastrado(@PathVariable String email){
        Boolean isCadastrado = artesaoService.isEmailCadastrado(email);
        return isCadastrado;
    }

    @DeleteMapping(value = "/excluir/{id}")
    public Boolean deleteUsuario(@PathVariable Long id) {
        if (!artesaoService.buscarId(id).isPresent()) {
            return false;
        }
        artesaoService.deletarUsuario(id);
        return true;
    }

    @GetMapping(value = "/listarId/{id}")
    public ArtesaoDTO buscarId(@PathVariable Long id){
        Optional<Artesao> user = artesaoService.buscarId(id);
        
        if(user.get() != null){
            ArtesaoDTO buscando = new ArtesaoDTO(user.get());
            return buscando;
        }

        return null;        
    }

    @PutMapping(value = "/alterarUsuario/{id}")
    public ArtesaoDTO alterarUsuario(@PathVariable Long id, @RequestBody ArtesaoDTO usuarioDTO){
         
        return artesaoService.atualizarUsuario(id, usuarioDTO);
    }

}


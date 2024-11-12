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

import com.projeto.artprice.dto.UsuarioDTO;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;
    
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
    public UsuarioDTO cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.cadastrarUsuario(usuarioDTO);
    }

    @GetMapping(value = "/listar-usuarios")
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
    }

    @GetMapping(value = "/verificarEmail/{email}")
    public Boolean verificarEmailCadastrado(@PathVariable String email){
        Boolean isCadastrado = usuarioService.isEmailCadastrado(email);
        return isCadastrado;
    }

    @DeleteMapping(value = "/excluir/{id}")
    public Boolean deleteUsuario(@PathVariable Long id) {
        if (!usuarioService.buscarId(id).isPresent()) {
            return false;
        }
        usuarioService.deletarUsuario(id);
        return true;
    }

    @GetMapping(value = "/listarId/{id}")
    public UsuarioDTO buscarId(@PathVariable Long id){
        Optional<Usuario> user = usuarioService.buscarId(id);
        
        if(user.get() != null){
            UsuarioDTO buscando = new UsuarioDTO(user.get());
            return buscando;
        }

        return null;        
    }

    @PutMapping(value = "/alterarUsuario/{id}")
    public UsuarioDTO alterarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
         
        return usuarioService.atualizarUsuario(id, usuarioDTO);
    }

}


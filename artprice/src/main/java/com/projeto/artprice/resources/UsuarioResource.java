package com.projeto.artprice.resources;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO novoUsuario = usuarioService.cadastrarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping(value = "/listar-usuarios")
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
    }

    @GetMapping(value = "/verificarEmail/{email}")
    public Boolean verificarEmailCadastrado(@PathVariable String email){
        Boolean isCadastrado = usuarioService.isEmailCadastrado(email);
        
        if(isCadastrado){
            return true;
        }

        return false;        
    }

    @DeleteMapping(value = "/excluir/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        if (!usuarioService.buscarId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + id + " não encontrado.");
        }
    
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok("Usuário com ID " + id + " excluído com sucesso.");
    }

    @GetMapping(value = "/listarId/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id){
        Optional<Usuario> user = usuarioService.buscarId(id);
        
        if(user.get() != null){
            UsuarioDTO buscando = new UsuarioDTO(user.get());
            return ResponseEntity.ok(buscando);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + id + " não encontrado.");        
    }

    @PutMapping(value = "/alterarUsuario/{id}")
    public ResponseEntity<?> alterarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO userAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);
        if(userAtualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + id + " não encontrado.");       
        }

        return ResponseEntity.ok().body(userAtualizado);
    }

}


package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.dto.CepDTO;
import com.projeto.artprice.model.Cep;
import com.projeto.artprice.model.Endereco;
import com.projeto.artprice.model.Usuario;
import com.projeto.artprice.service.CepService;
import com.projeto.artprice.service.EnderecoService;
import com.projeto.artprice.service.UsuarioService;


@RestController
@RequestMapping(value = "/api")
public class UsuarioResource {
	@Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private CepService cepService;

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
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        
        // Verifica os campos obrigatórios
        Endereco endereco = usuario.getEndereco();
        if (endereco == null || endereco.getCep_id() == null || endereco.getNumero() == null || endereco.getComplemento() == null) {
            throw new IllegalArgumentException("CEP, número e complemento são obrigatórios.");
        }
    
        // Verifica CEP
        Cep cep = endereco.getCep_id();
        String cepFormatado = cep.getCep().replace("-", "");
        
        // Consulta o CEP no banco
        Cep cepExistente = cepService.consultarCep(cepFormatado);
    
        if (cepExistente == null) {
            // CEP não encontrado no banco, consulta a API ViaCEP
            CepDTO cepDTO = cepService.consultarCepApi(cepFormatado);
    
            if (cepDTO != null) {
                // Verifica se os dados retornados estão ausentes (indicando uma cidade inteira)
                if (cepDTO.getLogradouro() == null || cepDTO.getLogradouro().isEmpty() || 
                    cepDTO.getBairro() == null || cepDTO.getBairro().isEmpty()) {
                    // O CEP cobre a cidade inteira (ou uma área ampla), o usuário deve fornecer logradouro e bairro
                    if (endereco.getLogradouro() == null || endereco.getBairro() == null) {
                        throw new IllegalArgumentException("Logradouro e bairro são obrigatórios para CEPs de cidade inteira.");
                    }
                } else {
                    // Se não for uma cidade inteira, preenche os dados automaticamente com o que veio da API
                    endereco.setLogradouro(cepDTO.getLogradouro());
                    endereco.setBairro(cepDTO.getBairro());
                }
                
                cep.setCep(cepDTO.getCep());
                cep.setCidade(cepDTO.getLocalidade());
                cep.setEstado(cepDTO.getUf());
                
                // Salva o novo CEP no banco
                cepExistente = cepService.cadastrarCep(cep);
            }
        }
    
<<<<<<< HEAD
        // Associa o CEP existente ao endereço
        endereco.setCep_id(cepExistente);
    
        // Salva o endereço no banco de dados
        enderecoService.salvarEndereco(endereco);
=======
        //busca o cep no banco
        Cep buscaCep = cepService.buscarCep(cep);
        if(buscaCep != null){
            
        }
    
        // Salva Endereço
        Endereco enderecoSalvo = enderecoService.cadastrarEnd(endereco);
        usuario.setEndereco(enderecoSalvo);
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
    
        // Salva o usuário com o endereço associado
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.ok().body(novoUsuario);
    }

}


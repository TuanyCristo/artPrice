package com.projeto.artprice.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.artprice.service.ClienteService;
import com.projeto.artprice.dto.ClienteDTO;
import com.projeto.artprice.model.Cliente;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/cadastrarCliente")
    public ClienteDTO cadastrarCliente(@RequestBody ClienteDTO cliente) {      
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping(value = "/lerCliente/{telefone}")
    public ClienteDTO verCliente(@PathVariable String telefone){
        return clienteService.buscarCliente(telefone);
    }

    @PutMapping(value = "/alterarCliente/{id}")
    public ClienteDTO alterarCliente(@PathVariable Long id, @RequestBody ClienteDTO cliente){
        ClienteDTO alterado = clienteService.alterarCliente(cliente, id);
        return alterado;
    }

    @DeleteMapping(value = "/excluirCliente/{id}")
    public Boolean excluirCliente(@PathVariable Long id){
        Optional<Cliente> excluir = clienteService.buscarCliente(id);
        if(excluir.isPresent()){
            clienteService.excluirCliente(id);
            return true;
        } 
        return false;
    }

    @GetMapping("/listarClientes")
    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        return clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
    }
    
    

}

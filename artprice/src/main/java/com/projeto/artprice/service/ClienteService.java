package com.projeto.artprice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.dto.ClienteDTO;
import com.projeto.artprice.model.Cliente;
import com.projeto.artprice.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO cadastrarCliente(ClienteDTO cliente){
        Cliente novoCliente = new Cliente();
        if(cliente != null){
            novoCliente.setNome(cliente.getNome());
            novoCliente.setTelefone(cliente.getTelefone());
            Cliente cadastrado = clienteRepository.save(novoCliente);
            return new ClienteDTO(cadastrado);
        }
        return null;
    }

    public ClienteDTO buscarCliente(String telefone){
        return new ClienteDTO(clienteRepository.findByTelefone(telefone));
    }

    public void excluirCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO alterarCliente(ClienteDTO cliente, Long id){
        Cliente buscar = clienteRepository.findById(id).get();
        if(buscar != null){
            if(!cliente.getNome().equals(buscar.getNome())){
                buscar.setNome(cliente.getNome());
            }
            if(!cliente.getTelefone().equals(buscar.getTelefone())){
                buscar.setTelefone(cliente.getTelefone());
            }

            Cliente atualizado = clienteRepository.save(buscar);
            return new ClienteDTO(atualizado);
        }
        return null;
    }

    public Optional<Cliente> buscarCliente(Long id){
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }



    
}

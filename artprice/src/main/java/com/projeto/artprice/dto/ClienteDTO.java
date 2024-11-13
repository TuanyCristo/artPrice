package com.projeto.artprice.dto;

import com.projeto.artprice.model.Cliente;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    private String telefone;

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getNome();
    }
}



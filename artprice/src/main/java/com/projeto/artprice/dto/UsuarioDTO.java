package com.projeto.artprice.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;

}

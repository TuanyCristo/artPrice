package com.projeto.artprice.dto;

import com.projeto.artprice.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.telefone = usuario.getTelefone();
        this.cep = usuario.getEndereco().getCep().getCep();
        this.logradouro = usuario.getEndereco().getLogradouro();
        this.numero = usuario.getEndereco().getNumero();
        this.bairro = usuario.getEndereco().getBairro();
        this.complemento = usuario.getEndereco().getComplemento();
        this.cidade = usuario.getEndereco().getCep().getCidade();
        this.estado = usuario.getEndereco().getCep().getEstado();
    }
}

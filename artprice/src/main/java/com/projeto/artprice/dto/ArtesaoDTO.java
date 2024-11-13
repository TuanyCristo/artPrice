package com.projeto.artprice.dto;

import com.projeto.artprice.model.Artesao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtesaoDTO {
    private Long id;
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

    public ArtesaoDTO(Artesao artesao) {
        this.id = artesao.getId();
        this.nome = artesao.getNome();
        this.email = artesao.getEmail();
        this.senha = artesao.getSenha();
        this.telefone = artesao.getTelefone();
        this.cep = artesao.getEndereco().getCep().getCep();
        this.logradouro = artesao.getEndereco().getLogradouro();
        this.numero = artesao.getEndereco().getNumero();
        this.bairro = artesao.getEndereco().getBairro();
        this.complemento = artesao.getEndereco().getComplemento();
        this.cidade = artesao.getEndereco().getCep().getCidade();
        this.estado = artesao.getEndereco().getCep().getEstado();
    }
}

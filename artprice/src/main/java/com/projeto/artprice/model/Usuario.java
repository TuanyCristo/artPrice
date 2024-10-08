package com.projeto.artprice.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data //getters e setters criados pelo lombok
public class Usuario {
    @Id //chave primária do db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private int id;

    @NotBlank //anotação para não deixar que o campo fique nullo
    @Size(min = 3, max = 100) //anotação para exigir o tamanho mínimo do nome
    private String nome;

    @NotBlank
    @Email //anotação para email valido
    private String email;

    @NotBlank
    @Size(min = 6, max = 12) //anotação para exigir o tamanho da senha
    private String senha; 

    @ManyToOne //anotação para garantir que cada usuário tenha um endereço
    private Endereco endereco;

    @NotBlank
    private String telefone;
}

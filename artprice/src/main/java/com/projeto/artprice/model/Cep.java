package com.projeto.artprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //gera getters e setters pelo lombok
@NoArgsConstructor //cria o construtor sem argumentos
@AllArgsConstructor //cria o contrutor com todos os argumentos
public class Cep {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String cep;

    @NotNull
    private String logradouro;

    @NotNull
    private String bairro;

    @NotNull
    private String estado;

    @NotNull
    private String cidade;

}

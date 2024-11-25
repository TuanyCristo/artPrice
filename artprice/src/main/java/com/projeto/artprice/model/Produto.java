package com.projeto.artprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor //cria o construtor sem argumentos
@AllArgsConstructor //cria o contrutor com todos os argumentos
public class Produto {

    @Id //chave primária do db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera automático
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String url;


    @NotNull
    private double valor;
}

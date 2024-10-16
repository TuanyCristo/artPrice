package com.projeto.artprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //gera getters e setters
@NoArgsConstructor //cria o construtor sem argumentos
@AllArgsConstructor //cria o contrutor com todos os argumentos
public class Endereco {
    @Id //chave primária do db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private int id;

    @ManyToOne //anotação para dizer que muitos endereços podem ter o mesmo cep
    @JoinColumn(name = "cep_id")
    private Cep cep;

    @NotNull
    private String logradouro;

    @NotNull
    private String numero;

    
    @NotNull
    private String bairro;

    private String complemento;

}
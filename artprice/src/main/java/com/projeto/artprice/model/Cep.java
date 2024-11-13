package com.projeto.artprice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //gera getters e setters pelo lombok
@NoArgsConstructor //cria o construtor sem argumentos
@AllArgsConstructor //cria o contrutor com todos os argumentos
public class Cep {
    @Id
    @Column(name = "id_cep")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido") //valida o formato do cep
    @Column(unique = true)
    private String cep;


    @NotNull
    private String estado;

    @NotNull
    private String cidade;


}



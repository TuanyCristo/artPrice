package com.projeto.artprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
<<<<<<< HEAD
import jakarta.validation.constraints.Pattern;
=======
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
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

<<<<<<< HEAD
    @NotNull
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "CEP inválido") //valida o formato do cep
    private String cep;

   
    private String cidade;

    
=======
    private String cep;

    @NotNull
    private String logradouro;

    @NotNull
    private String bairro;

    @NotNull
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
    private String estado;

    @NotNull
    private String cidade;

}

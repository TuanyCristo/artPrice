package com.projeto.artprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data //gera getters e setters pelo lombok
public class Cep {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String codigoCep;

    private String cidade;

    private String estado;

}

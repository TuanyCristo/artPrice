package com.projeto.artprice.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data //getters e setters criados pelo lombok
public class Usuario {
    @Id //chave primária do db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private int id;



}

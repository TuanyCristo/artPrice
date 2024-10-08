package com.projeto.artprice.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data //gera getters e setters
public class Endereco {
    @Id //chave primária do db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private int id;

    @ManyToOne //anotação para dizer que muitos endereços podem ter o mesmo cep
    @JoinColumn(name = "cep_id", referencedColumnName = "id") // Define o nome da coluna no bd e a coluna referenciada
    private Cep cepId;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

}

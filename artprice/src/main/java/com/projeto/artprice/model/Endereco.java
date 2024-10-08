package com.projeto.artprice.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @JoinColumn(name = "cep_id", referencedColumnName = "id") // Define o nome da coluna no bd e a coluna referenciada
    private Cep cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

}

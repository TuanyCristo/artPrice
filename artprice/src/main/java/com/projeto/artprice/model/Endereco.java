package com.projeto.artprice.model;

<<<<<<< HEAD
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
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
    private Cep cep_id;

<<<<<<< HEAD
    
    @Size(min = 4, max = 250)
    private String logradouro;

    @NotNull
    @Size(min = 1, max = 8)
=======
    @NotNull
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4
    private String numero;


    private String complemento;

<<<<<<< HEAD
    
    private String bairro;
=======
>>>>>>> d3aacaa76b64cdf44a89c7fb82fc04d190ba3ab4

}

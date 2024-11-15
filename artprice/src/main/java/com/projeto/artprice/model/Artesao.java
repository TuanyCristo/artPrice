package com.projeto.artprice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor //cria o construtor sem argumentos
@AllArgsConstructor //cria o contrutor com todos os argumentos
@Data
@EqualsAndHashCode(callSuper = true) //impede que o lombok duplique os getters e setter que já tem na classe pai
public class Artesao extends Usuario {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Email //anotação para email valido
    @Column(unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 12) //anotação para exigir o tamanho da senha
    private String senha; 

    @NotNull
    @ManyToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;



}

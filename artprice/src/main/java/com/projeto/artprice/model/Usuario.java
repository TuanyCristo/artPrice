package com.projeto.artprice.model;

import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //getters e setters criados pelo lombok
@NoArgsConstructor //cria o construtor sem argumentos
@AllArgsConstructor //cria o contrutor com todos os argumentos
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id //chave primária do db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;

    @NotBlank //anotação para não deixar que o campo fique nullo
    @Size(min = 3, max = 100) //anotação para exigir o tamanho mínimo do nome
    private String nome;

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

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String telefone;

    private String permissao = "padrao";


}

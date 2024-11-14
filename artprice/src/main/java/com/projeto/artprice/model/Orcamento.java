package com.projeto.artprice.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // Cria o construtor sem argumentos
@AllArgsConstructor // Cria o construtor com todos os argumentos
public class Orcamento {

    @Id // Chave primária do db
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera automaticamente
    private Long id;

    @NotNull
    @ManyToOne
    private Artesao artesao;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    private LocalDate dataOrcamento;

    @NotNull
    private LocalDate dataValidade;

    @NotBlank
    private double frete;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Relacionamento Um-para-Muitos com Produto
    private List<Produto> listaProdutos;

    @NotNull
    private double valorTotal;
}

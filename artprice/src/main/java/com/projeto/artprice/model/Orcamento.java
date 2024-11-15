package com.projeto.artprice.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // Cria o construtor sem argumentos
@AllArgsConstructor // Cria o construtor com todos os argumentos
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artesao_id")
    private Artesao artesao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "orcamento_produto", 
        joinColumns = @JoinColumn(name = "orcamento_id"), 
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )

    @NotNull
    private List<Produto> produtos;

    @NotNull
    private LocalDate dataOrcamento;
    @NotNull
    private LocalDate dataValidade;

    @NotNull
    private double frete;

    private double desconto;

    @NotNull
    private double valorTotal;

}

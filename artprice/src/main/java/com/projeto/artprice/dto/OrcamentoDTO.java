package com.projeto.artprice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.artprice.model.Artesao;
import com.projeto.artprice.model.Cliente;
import com.projeto.artprice.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoDTO {
    private Cliente cliente;
    private Artesao artesao;
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dataOrcamento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dataValidade;
    private double frete;
    private List<Produto> listaProdutos;
    private double valorTotal;

}

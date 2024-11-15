package com.projeto.artprice.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.projeto.artprice.model.Orcamento;
import com.projeto.artprice.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoDTO {
    private Long id;
    private Long idCliente;
    private Long idArtesao;
    private LocalDate dataOrcamento;
    private LocalDate dataVencimento;
    private double frete;
    private double valorTotal;
    private double desconto;
    private List<Long> listaProdutos;

    public OrcamentoDTO (Orcamento orcamento){
        this.id = orcamento.getId();
        this.idCliente = orcamento.getCliente().getId();
        this.idArtesao = orcamento.getArtesao().getId();
        this.dataOrcamento = orcamento.getDataOrcamento();
        this.dataVencimento = orcamento.getDataValidade();
        this.frete = orcamento.getFrete();
        this.valorTotal = orcamento.getValorTotal();
        this.desconto = orcamento.getDesconto();
        this.listaProdutos = orcamento.getProdutos().stream().map(Produto::getId).collect(Collectors.toList());
    }

}

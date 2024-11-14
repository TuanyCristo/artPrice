package com.projeto.artprice.dto;

import java.util.List;

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
    private List<Long> listaProdutos;

}

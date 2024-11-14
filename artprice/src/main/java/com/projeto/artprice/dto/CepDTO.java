package com.projeto.artprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepDTO {
    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String bairro;
}

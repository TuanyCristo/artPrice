package com.projeto.artprice.dto;

import lombok.Data;

@Data
public class CepDTO {
    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String bairro;
}

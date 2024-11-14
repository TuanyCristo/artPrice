package com.projeto.artprice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.dto.OrcamentoDTO;
import com.projeto.artprice.model.Orcamento;
import com.projeto.artprice.service.OrcamentoService;

@RestController
@RequestMapping(value = "/orcamento")
public class OrcamentoResource {

    @Autowired
    private OrcamentoService orcamentoService;

    @PostMapping(value = "/cadastrarOrcamento")
    public Orcamento cadastrarOrcamento(@RequestBody OrcamentoDTO orcamento){
        return orcamentoService.cadastrarOrcamento(orcamento);
    }


}


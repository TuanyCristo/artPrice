package com.projeto.artprice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.artprice.dto.OrcamentoDTO;
import com.projeto.artprice.model.Orcamento;
import com.projeto.artprice.service.OrcamentoService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value = "/orcamento")
public class OrcamentoResource {

    @Autowired
    private OrcamentoService orcamentoService;

    @PostMapping(value = "/cadastrarOrcamento")
    public Orcamento cadastrarOrcamento(@RequestBody OrcamentoDTO orcamento){
        return orcamentoService.cadastrarOrcamento(orcamento);
    }

    @GetMapping(value = "/verOrcamento/{id}")
    public Orcamento verOrcamento(@PathVariable Long id) {
        return orcamentoService.verOrcamento(id);
    }

    @PutMapping(value = "/alterarOrcamento/{id}")
    public Orcamento alterarOrcamento(@PathVariable Long id, @RequestBody OrcamentoDTO orcamento) { 
        return orcamentoService.alterarOrcamento(id, orcamento);
    }

    @DeleteMapping(value = "/excluirOrcamento/{id}")
    public Boolean excluirOrcamento(@PathVariable Long id){
        Orcamento orcamento = orcamentoService.verOrcamento(id);
        if(orcamento != null){
            orcamentoService.deletarOrcamento(id);
            return true;
        }
        return false;
    }

    @GetMapping("/listaOrcamentoCli/{clienteId}")
    public List<Orcamento> listarOrcamentosPorCliente(@PathVariable Long clienteId) {
        return orcamentoService.listarOrcamentosPorCliente(clienteId);
    }

    @GetMapping("/listaOrcamentoArtesao/{artesaoId}")
    public List<Orcamento> listarOrcamentosPorArtesao(@PathVariable Long artesaoId) {
        return orcamentoService.listarOrcamentosPorArtesao(artesaoId);
    }
    


}


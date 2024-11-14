package com.projeto.artprice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.artprice.dto.OrcamentoDTO;
import com.projeto.artprice.model.Artesao;
import com.projeto.artprice.model.Cliente;
import com.projeto.artprice.model.Orcamento;
import com.projeto.artprice.model.Produto;
import com.projeto.artprice.repository.ArtesaoRepository;
import com.projeto.artprice.repository.ClienteRepository;
import com.projeto.artprice.repository.OrcamentoRepository;
import com.projeto.artprice.repository.ProdutoRepository;

@Service
public class OrcamentoService {
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private ArtesaoRepository artesaoRepository;

    @Autowired 
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Orcamento cadastrarOrcamento(OrcamentoDTO orcamentoDTO){

        // Define a data do orçamento e a data de validade
        orcamento.setDataOrcamento(LocalDate.now());
        orcamento.setDataValidade(orcamento.getDataOrcamento().plusDays(10));

        // Calcula o valor total dos produtos
        double totalProdutos = orcamento.getProdutos().stream()
            .mapToDouble(Produto::getValor)
            .sum();

        // Define o valor total (produtos + frete - desconto)
        orcamento.setValorTotal(totalProdutos + orcamento.getFrete() - orcamento.getDesconto());
        
        Optional<Artesao> artesao = artesaoRepository.findById(orcamentoDTO.getIdArtesao());

        Optional<Cliente> cliente = clienteRepository.findById(orcamentoDTO.getIdCliente());

        // Buscar todos os produtos usando os IDs da lista
        List<Produto> produtos = produtoRepository.findAllById(orcamentoDTO.getListaProdutos());

        // Criando o objeto Orcamento e associando os dados
        Orcamento orcamento = new Orcamento();
        orcamento.setArtesao(artesao.get());
        orcamento.setCliente(cliente.get());
        orcamento.setProdutos(produtos);

        // Persistindo no banco de dados
        return orcamentoRepository.save(orcamento);
}

}

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
        orcamentoDTO.setDataOrcamento(LocalDate.now());
        orcamentoDTO.setDataVencimento(orcamentoDTO.getDataOrcamento().plusDays(10));

        // Calcula o valor total dos produtos
        List<Produto> listaProdutos = produtoRepository.findAllById(orcamentoDTO.getListaProdutos());
        double totalProdutos = listaProdutos.stream().mapToDouble(Produto::getValor).sum();

        // Define o valor total (produtos + frete - desconto)
        orcamentoDTO.setValorTotal(totalProdutos + orcamentoDTO.getFrete() - orcamentoDTO.getDesconto());
        
        Optional<Artesao> artesao = artesaoRepository.findById(orcamentoDTO.getIdArtesao());

        Optional<Cliente> cliente = clienteRepository.findById(orcamentoDTO.getIdCliente());

        // Criando o objeto Orcamento e associando os dados
        Orcamento orcamento = new Orcamento();
        orcamento.setArtesao(artesao.get());
        orcamento.setCliente(cliente.get());
        orcamento.setProdutos(listaProdutos);
        orcamento.setDataOrcamento(orcamentoDTO.getDataOrcamento());
        orcamento.setDataValidade(orcamentoDTO.getDataVencimento());
        orcamento.setDesconto(orcamentoDTO.getDesconto());
        orcamento.setFrete(orcamentoDTO.getFrete());
        orcamento.setValorTotal(orcamentoDTO.getValorTotal());

        // Persistindo no banco de dados
        return orcamentoRepository.save(orcamento);
}

    public Orcamento verOrcamento(Long id){
        Optional<Orcamento> orcamentoOptional = orcamentoRepository.findById(id);
        if(orcamentoOptional.isPresent()){
            return orcamentoOptional.get();
        }
        return null; 
    }

    public Orcamento alterarOrcamento(Long id, OrcamentoDTO orcamentoDTO) {
 
        Optional<Orcamento> orcamentoOptional = orcamentoRepository.findById(id);
        if (orcamentoOptional.isPresent()) {
            Orcamento orcamento = orcamentoOptional.get();

            orcamento.setDataOrcamento(orcamentoDTO.getDataOrcamento());
            orcamento.setDataValidade(orcamentoDTO.getDataVencimento());
            orcamento.setDesconto(orcamentoDTO.getDesconto());
            orcamento.setFrete(orcamentoDTO.getFrete());

            List<Produto> listaProdutos = produtoRepository.findAllById(orcamentoDTO.getListaProdutos());
            orcamento.setProdutos(listaProdutos);

            double totalProdutos = listaProdutos.stream().mapToDouble(Produto::getValor).sum();
            orcamento.setValorTotal(totalProdutos + orcamentoDTO.getFrete() - orcamentoDTO.getDesconto());

            return orcamentoRepository.save(orcamento);
        }
        return null;
    }

    public void deletarOrcamento(Long id){
        orcamentoRepository.deleteById(id);
    }

    public List<Orcamento> listarOrcamentosPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).get();
        
        if (cliente != null) {
            return orcamentoRepository.findByCliente(cliente);
        } else {

            return List.of(); 
        }
    }

    public List<Orcamento> listarOrcamentosPorArtesao(Long artesaoId) {
        Artesao artesao = artesaoRepository.findById(artesaoId).get();
        
        if (artesao != null) {
            return orcamentoRepository.findByArtesao(artesao);
        } else {

            return List.of(); 
        }
    }

}

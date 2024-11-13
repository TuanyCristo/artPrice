package com.projeto.artprice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.artprice.model.Artesao;


public interface ArtesaoRepository extends JpaRepository<Artesao, Long>{

    Artesao findByEmail(String email);

    public Artesao findByEmailAndSenha(String email, String senha);

}

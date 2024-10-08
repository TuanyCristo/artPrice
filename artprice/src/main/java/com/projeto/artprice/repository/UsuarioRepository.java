package com.projeto.artprice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.artprice.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}

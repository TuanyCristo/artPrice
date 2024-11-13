package com.projeto.artprice.validation;


import com.projeto.artprice.repository.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUnicoValidador implements ConstraintValidator<EmailUnico, String> {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // Verifica se o e-mail já existe no banco de dados
        if (email == null) {
            return false; // Se o e-mail for nulo, é inválido
        }
        return usuarioRepository.findByEmail(email) == null; // Se for null, está valido
    }
}

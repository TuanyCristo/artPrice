package com.projeto.artprice.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })// A anotação será aplicada a campos (ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailUnicoValidador.class)
public @interface EmailUnico {
    String message() default "O e-mail já está em uso";
    Class<?>[] groups() default {}; // Grupos de validação
    Class<? extends Payload>[] payload() default {};
}
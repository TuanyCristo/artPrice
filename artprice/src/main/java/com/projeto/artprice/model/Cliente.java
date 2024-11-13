package com.projeto.artprice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true) //impede que o lombok duplique os getters e setter que já tem na classe pai
public class Cliente extends Usuario {
        private static final long serialVersionUID = 1L;
    

        
}

package com.mintgestao.Domain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cidade {
    @Id
    private Long id;
    private String nome;

    @ManyToOne
    private Estado estado;
}

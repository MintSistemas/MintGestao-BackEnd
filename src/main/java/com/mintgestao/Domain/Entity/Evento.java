package com.mintgestao.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TenantId;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;
    @NotBlank
    @Size(min = 3, max = 100)
    private String sobrenome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String telefone;
    @NotNull
    @Positive
    private double valortotal;
    @NotNull
    @Positive
    private double valorhora;
    @NotBlank
    @Future
    private Date horainicio;
    @NotBlank
    @Future
    private Date horafim;
    @NotNull
    private Date datahoracadastro;

    @NotNull
    @ManyToOne
    private Local local;

    @ManyToOne
    private Cliente cliente;

    @TenantId
    private Integer idtenant;
}

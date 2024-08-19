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
    @NotBlank(message = "Nome é obrigatório e deve ter entre 3 e 100 caracteres")
    @Size(min = 3, max = 100)
    private String nome;
    @NotBlank(message = "Sobrenome é obrigatório e deve ter entre 3 e 100 caracteres")
    @Size(min = 3, max = 100)
    private String sobrenome;
    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @NotNull(message = "Valor total é obrigatório")
    @Positive
    private double valortotal;
    @NotNull(message = "Valor hora é obrigatório")
    @Positive
    private double valorhora;
    @NotNull(message = "Hora inicial é obrigatório")
    @Future
    private Date horainicio;
    @NotNull(message = "Hora final é obrigatório")
    @Future
    private Date horafim;
    @NotNull
    private Date datahoracadastro;

    @NotNull(message = "Local é obrigatório")
    @ManyToOne
    private Local local;

    @ManyToOne
    private Cliente cliente;

    @TenantId
    private Integer idtenant;
}

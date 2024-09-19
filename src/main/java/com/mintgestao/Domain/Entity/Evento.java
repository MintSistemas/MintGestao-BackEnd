package com.mintgestao.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TenantId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;
    @NotBlank(message = "Sobrenome é obrigatório e deve ter entre 3 e 100 caracteres")
    @Size(min = 3, max = 100, message = "Sobrenome deve ter entre 3 e 100 caracteres")
    private String sobrenome;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Insira um email válido")
    private String email;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @NotNull(message = "Valor total é obrigatório")
    @Positive(message = "Valor total deve ser maior que zero")
    private double valortotal;
    @NotNull(message = "Valor hora é obrigatório")
    @Positive(message = "Valor hora deve ser maior que zero")
    private double valorhora;
    @NotNull(message = "Hora inicial é obrigatório")
    private LocalTime horainicio;
    @NotNull(message = "Hora final é obrigatório")
    @Future(message = "Não é possível cadastrar um evento com a data menor que a atual")
    private LocalTime horafim;
    @NotNull(message = "Data é obrigatório")
    @Future(message = "Não é possível cadastrar um evento com a data menor que a atual")
    private LocalDate dataevento;

    private Date dataAlteracao = new Date();

    @NotNull(message = "Local é obrigatório")
    @ManyToOne
    private Local local;

    @ManyToOne
    private Cliente cliente;

    @TenantId
    private Integer idtenant;
}

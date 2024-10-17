package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusEvento;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TenantId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;
import jakarta.validation.constraints.AssertTrue;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long numero;

    @NotBlank(message = "Nome é obrigatório e deve ter entre 3 e 100 caracteres")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Insira um email válido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull(message = "Valor total é obrigatório")
    @Positive(message = "Valor total deve ser maior que zero")
    private BigDecimal valortotal;

    @NotNull(message = "Valor hora é obrigatório")
    @Positive(message = "Valor hora deve ser maior que zero")
    private BigDecimal valorhora;

    @NotNull(message = "Hora inicial é obrigatória")
    private LocalTime horainicio;

    @NotNull(message = "Hora final é obrigatória")
    private LocalTime horafim;

    @NotNull(message = "Data é obrigatória")
    @FutureOrPresent(message = "Não é possível cadastrar um evento com a data menor que a atual")
    private LocalDate dataevento;

    private EnumStatusEvento status;

    @NotNull
    private Date dataAlteracao = new Date();

    @NotNull(message = "Local é obrigatório")
    @ManyToOne
    @JoinColumn(name = "idlocal")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @TenantId
    private Integer idtenant;
}
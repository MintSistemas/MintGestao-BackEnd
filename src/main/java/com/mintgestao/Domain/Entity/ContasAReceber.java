package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusContasAReceber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContasAReceber {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long numero;

    private LocalDate dataevento;

    private LocalDate databaixa;

    private BigDecimal valor;

    private String observacao;

    @Enumerated(EnumType.ORDINAL)
    private EnumStatusContasAReceber status;

    @NotNull
    private Date dataalteracao = new Date();

    @NotNull(message = "O Contas a receber deve ser vinculado a um evento")
    @ManyToOne
    @JoinColumn(name = "idevento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @NotNull(message = "Local é obrigatório")
    @ManyToOne
    @JoinColumn(name = "idlocal")
    private Local local;
}

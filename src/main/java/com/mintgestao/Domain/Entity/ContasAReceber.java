package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusContasAReceber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Number numero;

    private LocalDate dataEvento;

    private LocalDate dataBaixa;

    private Float valor;

    private String observacao;

    @Enumerated(EnumType.ORDINAL)
    private EnumStatusContasAReceber status;

    @NotNull
    private Date dataAlteracao = new Date();

    @NotNull(message = "O Contas a receber deve ser vinculado a um evento")
    @OneToOne
    private Evento evento;

    @NotNull(message = "Cliente é obrigatório")
    @OneToOne
    private Cliente cliente;

    @NotNull(message = "Local é obrigatório")
    @OneToOne
    private Local local;
}

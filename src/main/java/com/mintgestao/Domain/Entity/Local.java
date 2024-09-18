package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusLocal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.TenantId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "Nome é obrigatório e deve ter entre 3 e 100 caracteres")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;
    @Enumerated(EnumType.ORDINAL)
    private EnumStatusLocal status;
    @NotBlank(message = "CEP é obrigatório")
    private String cep;
    @NotBlank(message = "Estado é obrigatório")
    private String estado;
    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;
    @NotBlank(message = "Rua é obrigatório")
    private String rua;
    @NotBlank(message = "Dias de funcionamento é obrigatório")
    private String diasFuncionamento;
    private String complemento;
    @NotNull(message = "Horário de abertura é obrigatório")
    private LocalDate horarioAbertura;
    @NotNull(message = "Horário de fechamento é obrigatório")
    private LocalDate horarioFechamento;
    private String observacao;
    @PositiveOrZero(message = "Valor hora deve positivo")
    private Double valorHora;

    @TenantId
    private Integer idtenant;
}
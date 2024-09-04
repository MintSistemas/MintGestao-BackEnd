package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusLocal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.TenantId;

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
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;
    @NotBlank(message = "Complemento é obrigatório")
    private String complemento;
    @NotBlank(message = "Observação é obrigatório")
    private String observacao;
    @NotNull(message = "Hora de abertura é obrigatório")
    private Date horaAbertura;
    @NotNull(message = "Hora de fechamento é obrigatório")
    private Date horaFechamento;
    @NotBlank(message = "Dias de funcionamento é obrigatório")
    private String diasFuncionamento;
    @NotNull(message = "Valor hora é obrigatório")
    @PositiveOrZero(message = "Valor hora deve positivo")
    private Double valorHora;

    @TenantId
    private Integer idtenant;
}
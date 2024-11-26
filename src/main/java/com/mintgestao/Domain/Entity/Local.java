package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusLocal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.TenantId;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private LocalTime horarioAbertura;
    @NotNull(message = "Horário de fechamento é obrigatório")
    private LocalTime horarioFechamento;
    private String observacao;
    @PositiveOrZero(message = "Valor hora deve positivo")
    private Double valorHora;

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ImagemLocal> imagens;

    private Date dataAlteracao = new Date();

    @TenantId
    private Integer idtenant;

    public List<DayOfWeek> getDiasFuncionamentoList() {
        return Arrays.stream(diasFuncionamento.split(","))
                .map(this::convertToDayOfWeek)
                .collect(Collectors.toList());
    }

    private DayOfWeek convertToDayOfWeek(String dia) {
        switch (dia.toLowerCase()) {
            case "dom": return DayOfWeek.SUNDAY;
            case "seg": return DayOfWeek.MONDAY;
            case "ter": return DayOfWeek.TUESDAY;
            case "qua": return DayOfWeek.WEDNESDAY;
            case "qui": return DayOfWeek.THURSDAY;
            case "sex": return DayOfWeek.FRIDAY;
            case "sab": return DayOfWeek.SATURDAY;
            default: throw new IllegalArgumentException("Dia da semana inválido: " + dia);
        }
    }
}
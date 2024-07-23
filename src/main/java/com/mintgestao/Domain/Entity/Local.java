package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusLocal;
import jakarta.persistence.*;
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
    private String nome;
    private EnumStatusLocal status;
    private String endereco;
    private String complemento;
    private String observacao;
    private Date horaAbertura;
    private Date horaFechamento;
    private String diasFuncionamento;

//    @TenantId
//    private Integer idtenant;
}
package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusCliente;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TenantId;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private EnumStatusCliente status;
    private String cpf;
    private String email;
    private String telefone;

    @TenantId
    private Integer idtenant;
}
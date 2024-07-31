package com.mintgestao.Domain.Entity;

import com.mintgestao.Domain.Enum.EnumStatusEmpresa;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TenantId;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String razaosocial;
    private String nomefantasia;
    private String apelido;
    private String email;
    private String telefone;
    private String ddd;
    private String endereco;
    private String numEndereco;
    private String cnpj;
    private Integer im;
    private Integer ie;
    private String estado;
    private String cidade;
    private String bairro;
    private String cep;
    private EnumStatusEmpresa status;
    private Boolean padrao;
    private Date datahoracad;

    @TenantId
    private Integer idtenant;
}
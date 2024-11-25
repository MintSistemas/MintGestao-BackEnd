package com.mintgestao.Domain.DTO.Local;

import com.mintgestao.Domain.Entity.ImagemLocal;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record LocalResponseDTO(
        String id,
        String nome,
        String status,
        String cep,
        String estado,
        String cidade,
        String bairro,
        String rua,
        String diasFuncionamento,
        String complemento,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        String observacao,
        Double valorHora,
        List<ImagemLocal> imagens,
        Date dataAlteracao,
        List<String> diasFuncionamentoList,
        String eventosDia
) {
}
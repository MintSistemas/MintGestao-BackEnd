package com.mintgestao.Domain.DTO.DashBoard;

import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Entity.Tema;
import com.mintgestao.Domain.Entity.Usuario;

import java.math.BigDecimal;
import java.util.List;

public record DashBoardResponseDTO(
        Double receitaTotal,
        Integer quantidadeEventosRecorrentes,
        Integer quantidadeEventos,
        Integer quantidadeEventosHoje,
        List<Evento> eventosAgendadosRecentemente
) {
}

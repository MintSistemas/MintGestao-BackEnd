package com.mintgestao.Domain.DTO.Evento;

import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Domain.Entity.Local;

import java.util.Date;

public record EventoRequestDTO(
        String nome,
        String sobrenome,
        String email,
        String telefone,
        double valortotal,
        double valorhora,
        Date horainicio,
        Date horafim,
        Date datahoracadastro,
        Local local,
        Cliente cliente
) {
}

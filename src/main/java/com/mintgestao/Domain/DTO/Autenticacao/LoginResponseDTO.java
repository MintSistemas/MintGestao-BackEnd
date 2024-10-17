package com.mintgestao.Domain.DTO.Autenticacao;

import com.mintgestao.Domain.Entity.Tema;
import com.mintgestao.Domain.Entity.Usuario;

public record LoginResponseDTO(
        Usuario usuario,
        String token,
        String refreshToken,
        Tema tema
) {
}

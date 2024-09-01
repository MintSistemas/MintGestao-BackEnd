package com.mintgestao.Domain.DTO.Login;

import com.mintgestao.Domain.Entity.Tema;
import com.mintgestao.Domain.Entity.Usuario;

public record LoginResponseDTO(
        Usuario usuario,
        String token,
        String refreshToken,
        Tema tema
) {
}

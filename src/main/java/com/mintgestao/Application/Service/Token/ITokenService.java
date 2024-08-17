package com.mintgestao.Application.Service.Token;

import com.mintgestao.Domain.Entity.Usuario;

import java.time.Instant;

public interface ITokenService {
    String gerarToken(Usuario usuario);
    Usuario validarToken(String token) throws Exception;
    String gerarRefreshToken(Usuario usuario);
    Usuario validarRefreshToken(String token);
    Instant getDataValidadeToken();
    Instant getDataValidadeRefreshToken();
}

package com.mintgestao.Domain.DTO.Autenticacao;

public record LoginRequestDTO(
    String email,
    String senha
) {
}

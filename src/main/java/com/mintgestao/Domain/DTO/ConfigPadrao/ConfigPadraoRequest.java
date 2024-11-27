package com.mintgestao.Domain.DTO.ConfigPadrao;

import java.util.UUID;

public record ConfigPadraoRequest(
    UUID local,
    UUID usuario
) {
}

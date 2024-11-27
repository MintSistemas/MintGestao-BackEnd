package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.ConfigPadraoService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.ConfigPadrao;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConfigPadraoUseCase extends UseCaseBase<ConfigPadrao> {

    public ConfigPadraoUseCase(ConfigPadraoService service) {
        super(service);
    }

    public ConfigPadrao recuperarConfigPadrao(UUID idUsuario) {
        return ((ConfigPadraoService) service).recuperarConfigPadrao(idUsuario);
    }
}

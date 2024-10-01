package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.TemaService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Tema;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TemaUseCase extends UseCaseBase<Tema> {
    public TemaUseCase(TemaService temaService) {
        super(temaService);
    }

    public Tema obterTemaPorUsuario(UUID id) throws Exception {
        try {
            return ((TemaService) service).obterTemaPorUsuario(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
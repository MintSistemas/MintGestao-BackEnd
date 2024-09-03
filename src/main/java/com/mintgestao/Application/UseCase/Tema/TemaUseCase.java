package com.mintgestao.Application.UseCase.Tema;

import com.mintgestao.Application.Service.Infrastructure.ServiceBase;
import com.mintgestao.Application.Service.Tema.TemaService;
import com.mintgestao.Application.UseCase.Infrastructure.UseCaseBase;
import com.mintgestao.Domain.Entity.Tema;
import com.mintgestao.Infrastructure.Repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
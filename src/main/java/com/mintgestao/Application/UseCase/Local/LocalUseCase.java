package com.mintgestao.Application.UseCase.Local;

import com.mintgestao.Application.Service.Local.LocalService;
import com.mintgestao.Application.UseCase.Infrastructure.UseCaseBase;
import com.mintgestao.Domain.Entity.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LocalUseCase extends UseCaseBase<Local> {

    public LocalUseCase(LocalService service) {
        super(service);
    }
}

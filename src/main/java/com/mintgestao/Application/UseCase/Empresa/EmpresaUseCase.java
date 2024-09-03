package com.mintgestao.Application.UseCase.Empresa;

import com.mintgestao.Application.Service.Empresa.EmpresaService;
import com.mintgestao.Application.UseCase.Infrastructure.UseCaseBase;
import com.mintgestao.Domain.Entity.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaUseCase extends UseCaseBase<Empresa> {

    public EmpresaUseCase(EmpresaService service) {
        super(service);
    }

}

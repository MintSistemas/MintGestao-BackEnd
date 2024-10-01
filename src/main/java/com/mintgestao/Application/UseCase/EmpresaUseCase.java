package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.EmpresaService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaUseCase extends UseCaseBase<Empresa> {

    public EmpresaUseCase(EmpresaService service) {
        super(service);
    }

}

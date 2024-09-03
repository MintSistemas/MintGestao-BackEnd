package com.mintgestao.Application.UseCase.Cliente;

import com.mintgestao.Application.Service.Cliente.ClienteService;
import com.mintgestao.Application.Service.Empresa.EmpresaService;
import com.mintgestao.Application.UseCase.Infrastructure.UseCaseBase;
import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Domain.Entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ClienteUseCase extends UseCaseBase<Cliente> {

    public ClienteUseCase(ClienteService service) {
        super(service);
    }
}

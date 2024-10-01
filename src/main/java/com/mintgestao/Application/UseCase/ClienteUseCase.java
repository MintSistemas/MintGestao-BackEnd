package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.ClienteService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteUseCase extends UseCaseBase<Cliente> {

    public ClienteUseCase(ClienteService service) {
        super(service);
    }
}

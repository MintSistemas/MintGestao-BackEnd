package com.mintgestao.Application.UseCase.Usuario;

import com.mintgestao.Application.Service.Usuario.UsuarioService;
import com.mintgestao.Application.UseCase.Infrastructure.UseCaseBase;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioUseCase extends UseCaseBase<Usuario> {

    public UsuarioUseCase(UsuarioService service) {
        super(service);
    }
}
package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.UsuarioService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioUseCase extends UseCaseBase<Usuario> {

    public UsuarioUseCase(UsuarioService service) {
        super(service);
    }
}
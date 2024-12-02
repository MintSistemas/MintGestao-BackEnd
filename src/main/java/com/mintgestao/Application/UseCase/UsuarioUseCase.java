package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.TemaService;
import com.mintgestao.Application.Service.UsuarioService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioUseCase extends UseCaseBase<Usuario> {

    public UsuarioUseCase(UsuarioService service) {
        super(service);
    }

    public List<Usuario> buscarTodosPorTenant(Integer idtenant) throws Exception {
        try {
            return ((UsuarioService) service).buscarTodosPorTenant(idtenant);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
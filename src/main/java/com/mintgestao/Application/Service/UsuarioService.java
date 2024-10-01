package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceBase<Usuario, UsuarioRepository> {
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
}
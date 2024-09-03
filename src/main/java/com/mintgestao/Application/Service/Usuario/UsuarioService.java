package com.mintgestao.Application.Service.Usuario;

import com.mintgestao.Application.Service.Infrastructure.ServiceBase;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService extends ServiceBase<Usuario, UsuarioRepository> {
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
}
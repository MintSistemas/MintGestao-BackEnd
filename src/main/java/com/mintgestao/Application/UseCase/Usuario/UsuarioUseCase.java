package com.mintgestao.Application.UseCase.Usuario;

import com.mintgestao.Application.Service.Usuario.IUsuarioService;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UsuarioUseCase implements IUsuarioUseCase {

    @Autowired
    private IUsuarioService usuarioService;

    public UsuarioUseCase(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Usuario> obterTodosUsuarios() {
        return usuarioService.obterTodosUsuarios();
    }

    @Override
    public Usuario obterUsuarioPorId(UUID id) {
        return usuarioService.obterUsuarioPorId(id);
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @Override
    public Boolean atualizarUsuario(UUID id, Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @Override
    public Boolean excluirUsuario(UUID id) {
        return usuarioService.excluirUsuario(id);
    }
}
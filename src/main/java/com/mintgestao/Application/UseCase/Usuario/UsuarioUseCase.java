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
    public List<Usuario> obterTodosUsuarios() throws Exception {
        try {
            return usuarioService.obterTodosUsuarios();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario obterUsuarioPorId(UUID id) throws Exception {
        try {
            return usuarioService.obterUsuarioPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) throws Exception {
        try {
            return usuarioService.criarUsuario(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarUsuario(UUID id, Usuario usuario) throws Exception {
        try {
            return usuarioService.atualizarUsuario(id, usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirUsuario(UUID id) throws Exception {
        try {
            return usuarioService.excluirUsuario(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
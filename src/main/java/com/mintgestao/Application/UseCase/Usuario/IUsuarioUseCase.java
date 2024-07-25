package com.mintgestao.Application.UseCase.Usuario;

import com.mintgestao.Domain.Entity.Usuario;

import java.util.List;
import java.util.UUID;

public interface IUsuarioUseCase {
    List<Usuario> obterTodosUsuarios();
    Usuario obterUsuarioPorId(UUID id);
    Usuario criarUsuario(Usuario usuario);
    Boolean atualizarUsuario(UUID id, Usuario usuario);
    Boolean excluirUsuario(UUID id);
}
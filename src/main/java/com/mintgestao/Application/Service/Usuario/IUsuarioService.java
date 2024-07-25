package com.mintgestao.Application.Service.Usuario;

import com.mintgestao.Domain.Entity.Usuario;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService {
    List<Usuario> obterTodosUsuarios();
    Usuario obterUsuarioPorId(UUID id);
    Usuario criarUsuario(Usuario usuario);
    Boolean atualizarUsuario(UUID id, Usuario usuario);
    Boolean excluirUsuario(UUID id);
}
package com.mintgestao.Application.Service.Usuario;

import com.mintgestao.Domain.Entity.Usuario;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService {
    List<Usuario> obterTodosUsuarios() throws Exception;
    Usuario obterUsuarioPorId(UUID id) throws Exception;
    Usuario criarUsuario(Usuario usuario) throws Exception;
    Boolean atualizarUsuario(UUID id, Usuario usuario) throws Exception;
    Boolean excluirUsuario(UUID id) throws Exception;
}
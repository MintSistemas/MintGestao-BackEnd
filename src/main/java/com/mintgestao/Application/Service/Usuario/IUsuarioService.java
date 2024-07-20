package com.mintgestao.Application.Service.Usuario;

import com.mintgestao.Domain.Entity.Usuario;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IUsuarioService {
    CompletableFuture<List<Usuario>> obterTodosUsuarios();
    CompletableFuture<Usuario> obterUsuarioPorId(UUID id);
    CompletableFuture<Usuario> criarUsuario(Usuario usuario);
    CompletableFuture<Boolean> atualizarUsuario(UUID id, Usuario usuario);
    CompletableFuture<Boolean> excluirUsuario(UUID id);
}
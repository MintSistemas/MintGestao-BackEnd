package com.mintgestao.Application.Service.Usuario;

import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public CompletableFuture<List<Usuario>> obterTodosUsuarios() {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.findAll(), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Usuario> obterUsuarioPorId(UUID id) {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.findById(id).orElse(null), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Usuario> criarUsuario(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> usuarioRepository.save(usuario), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> atualizarUsuario(UUID id, Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            if (usuarioRepository.existsById(id)) {
                usuario.setId(id);
                usuarioRepository.save(usuario);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> excluirUsuario(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }
}
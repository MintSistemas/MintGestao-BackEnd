package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Usuario.IUsuarioUseCase;
import com.mintgestao.Domain.Entity.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("gestao/usuario")
@Tag(name = "Usuario", description = "Endpoint responsavel pelo gerenciamento de usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioUseCase usuarioUseCase;

    public UsuarioController(IUsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Usuario>>> obterTodosUsuarios() {
        return usuarioUseCase.obterTodosUsuarios()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Usuario>> obterUsuarioPorId(@PathVariable UUID id) {
        return usuarioUseCase.obterUsuarioPorId(id)
                .thenApply(usuario -> usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Usuario>> criarUsuario(@RequestBody Usuario usuario) {
        return usuarioUseCase.criarUsuario(usuario)
                .thenApply(novoUsuario -> ResponseEntity.created(null).body(novoUsuario));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        return usuarioUseCase.atualizarUsuario(id, usuario)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> excluirUsuario(@PathVariable UUID id) {
        return usuarioUseCase.excluirUsuario(id)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }

}

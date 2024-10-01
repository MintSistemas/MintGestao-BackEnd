package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.UsuarioUseCase;
import com.mintgestao.Domain.Entity.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("gestao/usuario")
@Tag(name = "Usuario", description = "Endpoint responsável pelo gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioUseCase.buscarTodos();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable UUID id) {
        try {
            Usuario usuario = usuarioUseCase.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioUseCase.criar(usuario);
            return ResponseEntity.created(null).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        try {
            usuarioUseCase.atualizar(id, usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        try {
            usuarioUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

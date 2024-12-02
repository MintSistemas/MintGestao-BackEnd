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

    @GetMapping("/buscartodos/{idtenant}")
    public ResponseEntity<List<Usuario>> obterTodosUsuariosPorTenant(@PathVariable Integer idtenant) {
        try {
            List<Usuario> usuarios = usuarioUseCase.buscarTodosPorTenant(idtenant);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioUseCase.buscarTodos();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable UUID id) {
        try {
            Usuario usuario = usuarioUseCase.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        try {
            usuarioUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

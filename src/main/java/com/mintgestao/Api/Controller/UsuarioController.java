package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Usuario.IUsuarioUseCase;
import com.mintgestao.Domain.Entity.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("gestao/usuario")
@Tag(name = "Usuario", description = "Endpoint responsável pelo gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private IUsuarioUseCase usuarioUseCase;

    public UsuarioController(IUsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioUseCase.obterTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioUseCase.obterUsuarioPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioUseCase.criarUsuario(usuario);
        return ResponseEntity.created(null).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        Boolean result = usuarioUseCase.atualizarUsuario(id, usuario);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        Boolean result = usuarioUseCase.excluirUsuario(id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}

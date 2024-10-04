package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.LocalUseCase;
import com.mintgestao.Domain.Entity.Local;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/local")
@Tag(name = "Local", description = "Endpoint responsável pelo gerenciamento de locais/quadras")
public class LocalController {

    private LocalUseCase localUseCase;

    public LocalController(LocalUseCase localUseCase) {
        this.localUseCase = localUseCase;
    }

    @GetMapping
    public ResponseEntity obterTodosLocals() {
        try {
            List<Local> locals = localUseCase.buscarTodos();
            return ResponseEntity.ok(locals);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity criarLocal(@RequestBody Local local) {
        try {
            Local novoLocal = localUseCase.criar(local);
            return ResponseEntity.created(null).body(novoLocal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obterLocalPorId(@PathVariable UUID id) {
        try {
            Local local = localUseCase.buscarPorId(id);
            return ResponseEntity.ok(local);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarLocal(@PathVariable UUID id, @RequestBody Local local) {
        try {
            localUseCase.atualizar(id, local);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirLocal(@PathVariable UUID id) {
        try {
            localUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ativar")
    public ResponseEntity ativarLocal(@RequestBody List<UUID> ids) {
        try {
            localUseCase.ativar(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/inativar")
    public ResponseEntity desativarLocal(@RequestBody List<UUID> ids) {
        try {
            localUseCase.desativar(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

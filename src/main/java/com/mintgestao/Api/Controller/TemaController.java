package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.TemaUseCase;
import com.mintgestao.Domain.Entity.Tema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("configuracao/tema")
@Tag(name = "Tema", description = "Endpoint responsável pelo gerenciamento de temas")
public class TemaController {

    private final TemaUseCase temaUseCase;

    public TemaController(TemaUseCase temaUseCase) {
        this.temaUseCase = temaUseCase;
    }

    @GetMapping
    public ResponseEntity obterTodosTemas() {
        try{
            List<Tema> temas = temaUseCase.buscarTodos();
            return ResponseEntity.ok(temas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarporusuario/{id}")
    public ResponseEntity obterTemaPorUsuario(@PathVariable UUID id) {
        try {
            Tema tema = temaUseCase.obterTemaPorUsuario(id);
            return ResponseEntity.ok(tema);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obterTemaPorId(@PathVariable UUID id) {
        try {
            Tema tema = temaUseCase.buscarPorId(id);
            return ResponseEntity.ok(tema);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity criarTema(@Valid @RequestBody Tema tema) {
        try {
            Tema novoTema = temaUseCase.criar(tema);
            return ResponseEntity.ok(novoTema);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarTema(@PathVariable UUID id, @Valid @RequestBody Tema tema) {
        try {
            temaUseCase.atualizar(id, tema);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirTema(@PathVariable UUID id) {
        try {
            temaUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Local.ILocalUseCase;
import com.mintgestao.Domain.Entity.Local;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@RequestMapping("gestao/local")
@Tag(name = "Local", description = "Endpoint responsavel pelo gerenciamento de locais/quadras")
public class LocalController {

    @Autowired
    private ILocalUseCase localUseCase;

    public LocalController(ILocalUseCase localUseCase) {
        this.localUseCase = localUseCase;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Local>>> obterTodosLocals() {
        return localUseCase.obterTodosLocais()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Local>> obterLocalPorId(@PathVariable UUID id) {
        return localUseCase.obterLocalPorId(id)
                .thenApply(local -> local != null ? ResponseEntity.ok(local) : ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Local>> criarLocal(@RequestBody Local local) {
        return localUseCase.criarLocal(local)
                .thenApply(novoLocal -> ResponseEntity.created(null).body(novoLocal));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> atualizarLocal(@PathVariable UUID id, @RequestBody Local local) {
        return localUseCase.atualizarLocal(id, local)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> excluirLocal(@PathVariable UUID id) {
        return localUseCase.excluirLocal(id)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }
}
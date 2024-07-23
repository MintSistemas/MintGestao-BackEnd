package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Filial.IFilialUseCase;
import com.mintgestao.Domain.Entity.Filial;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@RequestMapping("gestao/filial")
@Tag(name = "Filial", description = "Endpoint responsavel pelo gerenciamento de filiais")
public class FilialController {

    @Autowired
    private IFilialUseCase filialUseCase;

    public FilialController(IFilialUseCase filialUseCase) {
        this.filialUseCase = filialUseCase;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Filial>>> obterTodosFilials() {
        return filialUseCase.obterTodosLocais()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Filial>> obterFilialPorId(@PathVariable UUID id) {
        return filialUseCase.obterFilialPorId(id)
                .thenApply(filial -> filial != null ? ResponseEntity.ok(filial) : ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Filial>> criarFilial(@RequestBody Filial filial) {
        return filialUseCase.criarFilial(filial)
                .thenApply(novoFilial -> ResponseEntity.created(null).body(novoFilial));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> atualizarFilial(@PathVariable UUID id, @RequestBody Filial filial) {
        return filialUseCase.atualizarFilial(id, filial)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> excluirFilial(@PathVariable UUID id) {
        return filialUseCase.excluirFilial(id)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }
}
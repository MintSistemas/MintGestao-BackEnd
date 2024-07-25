package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Local.ILocalUseCase;
import com.mintgestao.Domain.Entity.Local;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/local")
@Tag(name = "Local", description = "Endpoint responsável pelo gerenciamento de locais/quadras")
public class LocalController {

    @Autowired
    private ILocalUseCase localUseCase;

    public LocalController(ILocalUseCase localUseCase) {
        this.localUseCase = localUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Local>> obterTodosLocals() {
        List<Local> locais = localUseCase.obterTodosLocais();
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> obterLocalPorId(@PathVariable UUID id) {
        Local local = localUseCase.obterLocalPorId(id);
        return local != null ? ResponseEntity.ok(local) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Local> criarLocal(@RequestBody Local local) {
        Local novoLocal = localUseCase.criarLocal(local);
        return ResponseEntity.created(null).body(novoLocal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarLocal(@PathVariable UUID id, @RequestBody Local local) {
        Boolean result = localUseCase.atualizarLocal(id, local);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLocal(@PathVariable UUID id) {
        Boolean result = localUseCase.excluirLocal(id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

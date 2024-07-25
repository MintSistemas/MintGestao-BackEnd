package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Filial.IFilialUseCase;
import com.mintgestao.Domain.Entity.Filial;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/filial")
@Tag(name = "Filial", description = "Endpoint responsável pelo gerenciamento de filiais")
public class FilialController {

    @Autowired
    private IFilialUseCase filialUseCase;

    public FilialController(IFilialUseCase filialUseCase) {
        this.filialUseCase = filialUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Filial>> obterTodosFiliais() {
        List<Filial> filiais = filialUseCase.obterTodosLocais();
        return ResponseEntity.ok(filiais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filial> obterFilialPorId(@PathVariable UUID id) {
        Filial filial = filialUseCase.obterFilialPorId(id);
        return filial != null ? ResponseEntity.ok(filial) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Filial> criarFilial(@RequestBody Filial filial) {
        Filial novoFilial = filialUseCase.criarFilial(filial);
        return ResponseEntity.created(null).body(novoFilial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarFilial(@PathVariable UUID id, @RequestBody Filial filial) {
        Boolean result = filialUseCase.atualizarFilial(id, filial);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFilial(@PathVariable UUID id) {
        Boolean result = filialUseCase.excluirFilial(id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

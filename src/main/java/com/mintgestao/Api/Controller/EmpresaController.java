package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Empresa.IEmpresaUseCase;
import com.mintgestao.Domain.Entity.Empresa;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/empresa")
@Tag(name = "Empresa", description = "Endpoint responsável pelo gerenciamento de empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaUseCase empresaUseCase;

    public EmpresaController(IEmpresaUseCase empresaUseCase) {
        this.empresaUseCase = empresaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> obterTodosFiliais() {
        List<Empresa> filiais = empresaUseCase.obterTodosLocais();
        return ResponseEntity.ok(filiais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresaPorId(@PathVariable UUID id) {
        Empresa empresa = empresaUseCase.obterEmpresaPorId(id);
        return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        Empresa novoEmpresa = empresaUseCase.criarEmpresa(empresa);
        return ResponseEntity.created(null).body(novoEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEmpresa(@PathVariable UUID id, @RequestBody Empresa empresa) {
        Boolean result = empresaUseCase.atualizarEmpresa(id, empresa);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmpresa(@PathVariable UUID id) {
        Boolean result = empresaUseCase.excluirEmpresa(id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
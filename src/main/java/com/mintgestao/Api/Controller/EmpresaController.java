package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.EmpresaUseCase;
import com.mintgestao.Domain.Entity.Empresa;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/empresa")
@Tag(name = "Empresa", description = "Endpoint responsável pelo gerenciamento de empresas")
public class EmpresaController {

    private EmpresaUseCase empresaUseCase;

    public EmpresaController(EmpresaUseCase empresaUseCase) {
        this.empresaUseCase = empresaUseCase;
    }

    @GetMapping("/buscartodos")
    public ResponseEntity obterTodasEmpresas() {
        try {
            List<Empresa> empresas = empresaUseCase.buscarTodos();
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity obterEmpresaPorId(@PathVariable UUID id) {
        try {
            Empresa empresa = empresaUseCase.buscarPorId(id);
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/criar")
    public ResponseEntity criarEmpresa(@RequestBody Empresa empresa) {
        try {
            Empresa novaEmpresa = empresaUseCase.criar(empresa);
            return ResponseEntity.created(null).body(novaEmpresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarEmpresa(@PathVariable UUID id, @RequestBody Empresa empresa) {
        try {
            empresaUseCase.atualizar(id, empresa);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity excluirEmpresa(@PathVariable UUID id) {
        try {
            empresaUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
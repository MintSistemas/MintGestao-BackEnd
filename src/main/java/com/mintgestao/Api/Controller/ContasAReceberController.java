package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.ContasAReceberUseCase;
import com.mintgestao.Domain.Entity.ContasAReceber;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/contasareceber")
@Tag(name = "Contas a Receber", description = "Endpoint responsavel pelo gerenciamento de contas a receber")
public class ContasAReceberController {

    private ContasAReceberUseCase contasAReceberUseCase;

    public ContasAReceberController(ContasAReceberUseCase contasAReceberUseCase) {
        this.contasAReceberUseCase = contasAReceberUseCase;
    }

    @GetMapping
    public ResponseEntity obterTodasContasAReceber() {
        try {
            List<ContasAReceber> contasAReceber = contasAReceberUseCase.buscarTodos();
            return ResponseEntity.ok(contasAReceber);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obterContasAReceberPorId(@PathVariable UUID id) {
        try {
            ContasAReceber contasAReceber = contasAReceberUseCase.buscarPorId(id);
            return ResponseEntity.ok(contasAReceber);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity criarContasAReceber(@RequestBody ContasAReceber contasAReceber) {
        try {
            ContasAReceber novaContasAReceber = contasAReceberUseCase.criar(contasAReceber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarContasAReceber(@PathVariable UUID id, @RequestBody ContasAReceber contasAReceber) {
        try {
            contasAReceberUseCase.atualizar(id, contasAReceber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarContasAReceber(@PathVariable UUID id) {
        try {
            contasAReceberUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

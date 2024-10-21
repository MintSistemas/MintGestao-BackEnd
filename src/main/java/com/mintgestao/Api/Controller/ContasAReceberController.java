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

    @GetMapping("/buscartodos")
    public ResponseEntity obterTodasContasAReceber() {
        try {
            List<ContasAReceber> contasAReceber = contasAReceberUseCase.buscarTodos();
            return ResponseEntity.ok(contasAReceber);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity obterContasAReceberPorId(@PathVariable UUID id) {
        try {
            ContasAReceber contasAReceber = contasAReceberUseCase.buscarPorId(id);
            return ResponseEntity.ok(contasAReceber);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/criar")
    public ResponseEntity criarContasAReceber(@RequestBody ContasAReceber contasAReceber) {
        try {
            ContasAReceber novaContasAReceber = contasAReceberUseCase.criar(contasAReceber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarContasAReceber(@PathVariable UUID id, @RequestBody ContasAReceber contasAReceber) {
        try {
            contasAReceberUseCase.atualizar(id, contasAReceber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity deletarContasAReceber(@PathVariable UUID id) {
        try {
            contasAReceberUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/baixar/{id}")
    public ResponseEntity baixarContasAReceber(@PathVariable UUID id) {
        try {
            contasAReceberUseCase.baixar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity cancelarContasAReceber(@PathVariable UUID id) {
        try {
            contasAReceberUseCase.cancelarContasAReceber(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

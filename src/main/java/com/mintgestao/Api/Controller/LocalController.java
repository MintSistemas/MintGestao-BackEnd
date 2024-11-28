package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.LocalUseCase;
import com.mintgestao.Domain.DTO.Local.LocalResponseDTO;
import com.mintgestao.Domain.Entity.Local;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
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

    @GetMapping("/buscarultimasreservas/{id}")
    public ResponseEntity obterUltimasReservas(@PathVariable UUID id) {
        try {
            List<Local> locais = localUseCase.buscarUltimasReservas(id);
            return ResponseEntity.ok(locais);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filtrarlocais")
    public ResponseEntity<?> filtrarLocais(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String data,
            @RequestParam(required = false) String horaInicio,
            @RequestParam(required = false) String horaFim,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String cidade) {
        try {
            List<Local> locais = localUseCase.filtrarLocais(nome, data, horaInicio, horaFim, estado, cidade);
            return ResponseEntity.ok(locais);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity obterTodosLocals() {
        try {
            List<Local> locals = localUseCase.buscarTodos();
            return ResponseEntity.ok(locals);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarlocaisapp")
    public ResponseEntity obterLocaisApp() {
        try {
            List<Local> locals = localUseCase.buscarLocaisApp();
            return ResponseEntity.ok(locals);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarhorariosdisponiveis/{id}/{data}")
    public ResponseEntity obterLocalCardApp(@PathVariable UUID id, @PathVariable String data) {
        try {
            LocalResponseDTO local = localUseCase.buscarLocalCardApp(id, LocalDate.parse(data));
            return ResponseEntity.ok(local);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/criar")
    public ResponseEntity criarLocal(@RequestBody Local local) {
        try {
            Local novoLocal = localUseCase.criar(local);
            return ResponseEntity.created(null).body(novoLocal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity obterLocalPorId(@PathVariable UUID id) {
        try {
            Local local = localUseCase.buscarPorId(id);
            return ResponseEntity.ok(local);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarLocal(@PathVariable UUID id, @RequestBody Local local) {
        try {
            localUseCase.atualizar(id, local);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity excluirLocal(@PathVariable UUID id) {
        try {
            localUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ativar")
    public ResponseEntity ativarLocal(@RequestBody UUID id) {
        try {
            localUseCase.ativar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/inativar")
    public ResponseEntity inativarLocal(@RequestBody UUID id) {
        try {
            localUseCase.desativar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
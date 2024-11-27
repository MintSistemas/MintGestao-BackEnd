package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.ConfigPadraoUseCase;
import com.mintgestao.Domain.DTO.ConfigPadrao.ConfigPadraoRequest;
import com.mintgestao.Domain.Entity.ConfigPadrao;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Entity.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("configpadrao")
@CrossOrigin
@Tag(name = "Configuração Padrao", description = "Endpoint responsavel pela configuração padrão do sistema")
public class ConfigPadraoController {

    @Autowired
    private ConfigPadraoUseCase configPadraoUseCase;

    @GetMapping("/buscartodos")
    public ResponseEntity obterTodos() {
        try{
            List<ConfigPadrao> configPadraos = configPadraoUseCase.buscarTodos();
            return ResponseEntity.ok(configPadraos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarporusuario/{id}")
    public ResponseEntity obterPorUsuario(@PathVariable UUID id) {
        try {
            ConfigPadrao configPadrao = configPadraoUseCase.recuperarConfigPadrao(id);
            return ResponseEntity.ok(configPadrao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity obterPorId(@PathVariable UUID id) {
        try {
            ConfigPadrao configPadrao = configPadraoUseCase.buscarPorId(id);
            return ResponseEntity.ok(configPadrao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/criar")
    public ResponseEntity criar(@Valid @RequestBody ConfigPadrao configPadrao) {
        try {
            ConfigPadrao novaConfigPadrao = configPadraoUseCase.criar(configPadrao);
            return ResponseEntity.ok(novaConfigPadrao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable UUID id, @Valid @RequestBody ConfigPadrao configPadrao) {
        try {
            configPadraoUseCase.atualizar(id, configPadrao);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity excluir(@PathVariable UUID id) {
        try {
            configPadraoUseCase.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.DashBoardUseCase;
import com.mintgestao.Domain.DTO.DashBoard.DashBoardResponseDTO;
import com.mintgestao.Domain.Entity.Evento;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("gestao/dashboard")
@Tag(name = "DashBoard", description = "Endpoint responsavel pelas informações apresentadas no dashboard")
public class DashBoardController {

    private DashBoardUseCase dashBoardUseCase;

    public DashBoardController(DashBoardUseCase dashBoardUseCase) {
        this.dashBoardUseCase = dashBoardUseCase;
    }

    @GetMapping("/receitatotal")
    public ResponseEntity obterReceitaTotalPorPeriodo(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        try {
            Double receitaTotal = dashBoardUseCase.obterReceitaTotalPorPeriodo(dataInicio, dataFim);
            return ResponseEntity.ok(receitaTotal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/quantidadeeventosrecorrentes")
    public ResponseEntity obterQuantidadeEventosRecorrentes(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        try {
            Integer quantidadeEventosRecorrentes = dashBoardUseCase.obterQuantidadeEventosRecorrentes(dataInicio, dataFim);
            return ResponseEntity.ok(quantidadeEventosRecorrentes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/quantidadeeventos")
    public ResponseEntity obterQuantidadeEventos(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        try {
            Integer quantidadeEventos = dashBoardUseCase.obterQuantidadeEventos(dataInicio, dataFim);
            return ResponseEntity.ok(quantidadeEventos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/eventoshoje")
    public ResponseEntity obterEventosHoje() {
        try {
            Integer eventosHoje = dashBoardUseCase.obterQuantidadeEventosHoje();
            return ResponseEntity.ok(eventosHoje);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/eventosagendadosrecentemente")
    public ResponseEntity obterEventosAgendadosRecentemente() {
        try {
            List<Evento> eventosAgendadosRecentemente = dashBoardUseCase.obterEventosAgendadosRecentemente();
            return ResponseEntity.ok(eventosAgendadosRecentemente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/recuperardashBoard")
    public ResponseEntity recuperarDashBoard(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        try {
            DashBoardResponseDTO dto = dashBoardUseCase.recuperarDashBoard(dataInicio, dataFim);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

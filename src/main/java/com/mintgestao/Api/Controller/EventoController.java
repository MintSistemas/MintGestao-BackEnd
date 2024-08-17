package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Evento.EventoUseCase;
import com.mintgestao.Domain.Entity.Evento;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/evento")
@Tag(name = "Evento", description = "Endpoint responsável pelo gerenciamento de eventos")
public class EventoController {

    @Autowired
    private EventoUseCase eventoUseCase;

    public EventoController(EventoUseCase eventoUseCase) {
        this.eventoUseCase = eventoUseCase;
    }

    @GetMapping
    public ResponseEntity obterTodosEventos() {
        try{
            List<Evento> eventos = eventoUseCase.obterTodosEventos();
            return ResponseEntity.ok(eventos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obterEventoPorId(@PathVariable UUID id) {
        try {
            Evento evento = eventoUseCase.obterEventoPorId(id);
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity criarEvento(@RequestBody Evento evento) {
        try {
            Evento novoEvento = eventoUseCase.criarEvento(evento);
            return ResponseEntity.created(null).body(novoEvento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarEvento(@PathVariable UUID id, @RequestBody Evento evento) {
        try {
            eventoUseCase.atualizarEvento(id, evento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirEvento(@PathVariable UUID id) {
        try {
            eventoUseCase.excluirEvento(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.mintgestao.Application.UseCase.Evento;

import com.mintgestao.Application.Service.Evento.EventoService;
import com.mintgestao.Application.UseCase.Infrastructure.UseCaseBase;
import com.mintgestao.Domain.Entity.Evento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EventoUseCase extends UseCaseBase<Evento> {

    public EventoUseCase(EventoService service) {
        super(service);
    }

    public List<Evento> obterEventosPorLocal(UUID id) throws Exception {
        try {
            return ((EventoService) service).obterEventosPorLocal(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Evento criar(Evento evento) throws Exception {
        try {
            if (((EventoService) service).verificarDisponibilidade(evento)) {
                return ((EventoService) service).criar(evento);
            } else {
                throw new Exception("Já existe um evento cadastrado nesse horário");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
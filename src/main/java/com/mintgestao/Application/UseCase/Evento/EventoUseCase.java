package com.mintgestao.Application.UseCase.Evento;

import com.mintgestao.Application.Service.Evento.IEventoService;
import com.mintgestao.Application.Service.Local.ILocalService;
import com.mintgestao.Domain.Entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EventoUseCase implements IEventoUseCase {

    @Autowired
    private IEventoService eventoService;

    public EventoUseCase(IEventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Override
    public List<Evento> obterTodosEventos() throws Exception {
        try {
            return eventoService.obterTodosEventos();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Evento obterEventoPorId(UUID id) throws Exception {
        try {
            return eventoService.obterEventoPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Evento criarEvento(Evento evento) throws Exception {
        try {
            return eventoService.criarEvento(evento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void atualizarEvento(UUID id, Evento evento) throws Exception {
        try {
            eventoService.atualizarEvento(id, evento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void excluirEvento(UUID id) throws Exception {
        try {
            eventoService.excluirEvento(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
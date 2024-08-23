package com.mintgestao.Application.Service.Evento;

import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Infrastructure.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService implements IEventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public List<Evento> obterTodosEventos() throws Exception {
        try {
            return eventoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Evento> obterEventosPorLocal(UUID id) throws Exception {
        try {
            return eventoRepository.findByLocalId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Evento obterEventoPorId(UUID id) throws Exception {
        try {
            return eventoRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Evento criarEvento(Evento evento) throws Exception {
        try {
            return eventoRepository.save(evento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void atualizarEvento(UUID id, Evento evento) throws Exception {
        try {
            eventoRepository.findById(id).orElseThrow();
            evento.setId(id);
            eventoRepository.save(evento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void excluirEvento(UUID id) throws Exception {
        try {
            eventoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
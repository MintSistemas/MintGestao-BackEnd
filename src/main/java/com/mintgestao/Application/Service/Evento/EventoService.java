package com.mintgestao.Application.Service.Evento;

import com.mintgestao.Application.Service.Infrastructure.ServiceBase;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Infrastructure.Repository.EventoRepository;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService extends ServiceBase<Evento, EventoRepository> {

    public EventoService(EventoRepository repository) {
        super(repository);
    }

    public List<Evento> obterEventosPorLocal(UUID id) throws Exception {
        try {
            return repository.findByLocalId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean verificarDisponibilidade(Evento evento) {
        try {
            // Busca todos os eventos na mesma data
            List<Evento> eventos = repository.findByDataeventoAndLocalId(evento.getDataevento(), evento.getLocal().getId());

            for (Evento e : eventos) {
                // Se o evento novo começar ou terminar no meio de um evento existente
                if (evento.getHorainicio().isBefore(e.getHorafim()) && evento.getHorafim().isAfter(e.getHorainicio())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
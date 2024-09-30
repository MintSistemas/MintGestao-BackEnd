package com.mintgestao.Application.UseCase.Evento;

import com.mintgestao.Application.Service.Evento.EventoService;
import com.mintgestao.Application.UseCase.Infrastructure.UseCaseBase;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EventoUseCase extends UseCaseBase<Evento> {

    public EventoUseCase(EventoService service) {
        super(service);
    }

    @Autowired
    LocalRepository localRepository;

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
            Local local = localRepository.findById(evento.getLocal().getId()).get();
            ((EventoService) service).verificarDisponibilidade(evento);
            ((EventoService) service).varificarHorarioFuncionamento(evento, local);
            ((EventoService) service).verificarDiasFuncionamento(evento.getDataevento(), local.getDiasFuncionamentoList());

            return service.criar(evento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void atualizar(UUID id, Evento evento) throws Exception {
        try {
            Local local = localRepository.findById(evento.getLocal().getId()).get();
            evento.setId(id);

            ((EventoService) service).validarHoras(evento.getHorainicio(), evento.getHorafim());
            ((EventoService) service).verificarDisponibilidade(evento);
            ((EventoService) service).varificarHorarioFuncionamento(evento, local);
            ((EventoService) service).verificarDiasFuncionamento(evento.getDataevento(), local.getDiasFuncionamentoList());
            service.atualizar(id, evento);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
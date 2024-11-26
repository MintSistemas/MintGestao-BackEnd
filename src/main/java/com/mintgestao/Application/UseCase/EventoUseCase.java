package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.ContasAReceberService;
import com.mintgestao.Application.Service.EventoService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusEvento;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class EventoUseCase extends UseCaseBase<Evento> {

    public EventoUseCase(EventoService service) {
        super(service);
    }

    @Autowired
    LocalRepository localRepository;

    @Autowired
    ContasAReceberService contasAReceberService;

    public List<Evento> buscarEventosPorData(UUID idUsuario, LocalDate data) throws Exception {
        try {
            return ((EventoService) service).buscarEventosPorData(idUsuario, data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Evento> obterEventosPorLocal(UUID id) throws Exception {
        try {
            return ((EventoService) service).obterEventosPorLocal(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Evento criar(Evento evento) throws Exception {

        try {
            Local local = localRepository.findById(evento.getLocal().getId()).get();
            ((EventoService) service).verificarDisponibilidade(evento);
            ((EventoService) service).varificarHorarioFuncionamento(evento, local);
            ((EventoService) service).verificarDiasFuncionamento(evento.getDataevento(), local.getDiasFuncionamentoList());
            evento.setNumero(((EventoService) service).gerarProximoNumero());
            evento.setStatus(EnumStatusEvento.AguardandoPagto);
            service.criar(evento);
            contasAReceberService.gerarContasAReceber(evento);
            return evento;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void atualizar(UUID id, Evento evento) throws Exception {
        try {
            if(evento.getStatus() != EnumStatusEvento.AguardandoPagto) {
                throw new Exception("Não é possível alterar um evento cancelado ou pago");
            }

            Local local = localRepository.findById(evento.getLocal().getId()).get();
            evento.setId(id);

            ((EventoService) service).validarHoras(evento.getHorainicio(), evento.getHorafim());
            ((EventoService) service).verificarDisponibilidade(evento);
            ((EventoService) service).varificarHorarioFuncionamento(evento, local);
            ((EventoService) service).verificarDiasFuncionamento(evento.getDataevento(), local.getDiasFuncionamentoList());
            service.atualizar(id, evento);
            contasAReceberService.atualizarReceberComBaseNoEvento(evento);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void cancelarEvento(UUID id) throws Exception {
        try {
            contasAReceberService.cancelarContasAReceber(id);
            ((EventoService) service).cancelarEvento(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusEvento;
import com.mintgestao.Infrastructure.Repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventoService extends ServiceBase<Evento, EventoRepository> {

    public EventoService(EventoRepository repository) {
        super(repository);
    }

    public Long gerarProximoNumero() {
        return repository.findMaxNumero() + 1;
    }

    public List<Evento> obterEventosPorLocal(UUID id) throws Exception {
        try {
            return repository.findByLocalId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void verificarDisponibilidade(Evento evento) throws Exception {
        try {
            // Busca todos os eventos na mesma data
            List<Evento> eventos = repository.buscarPorDataEventoEIdLocal(evento.getDataevento(), evento.getLocal().getId());

            for (Evento e : eventos) {
                if(evento.getId() != null && evento.getId().equals(e.getId())) {
                    continue;
                }
                if (evento.getHorainicio().isBefore(e.getHorafim()) && evento.getHorafim().isAfter(e.getHorainicio())) {
                    throw new Exception("Já existe um evento cadastrado nesse horário");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void varificarHorarioFuncionamento(Evento evento, Local local) throws Exception {
        try {
            if (evento.getHorainicio().isBefore(local.getHorarioAbertura()) || evento.getHorafim().isAfter(local.getHorarioFechamento())) {
                throw new Exception("O evento não pode ser cadastrado fora do horário de funcionamento do local");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void verificarDiasFuncionamento(LocalDate dataEvento, List<DayOfWeek> diasFuncionamento) throws Exception {
        try {
            if (!diasFuncionamento.contains(dataEvento.getDayOfWeek())) {
                throw new Exception("O local não funciona nesse dia da semana");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void validarHoras(LocalTime horainicio, LocalTime horafim) throws Exception {
        try {
            if (!horafim.isAfter(horainicio)) {
                throw new Exception("A hora fim não pode ser menor que a hora inicial do evento");
            }
        } catch(Exception e){
                throw new Exception(e.getMessage());
        }
    }

    public Evento cancelarEvento(UUID id) throws Exception {
        try {
            Evento evento = repository.findById(id).orElseThrow(() -> new Exception("Evento não encontrado"));
            evento.setStatus(EnumStatusEvento.Cancelado);
            return repository.save(evento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
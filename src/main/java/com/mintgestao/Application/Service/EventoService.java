package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusEvento;
import com.mintgestao.Infrastructure.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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

    public List<Evento> buscarUltimasReservas(UUID idUsuario) throws Exception {
        try {
            return repository.buscarUltimasReservas(idUsuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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

    public Integer obterQuantidadeEventos(LocalDate dataInicio, LocalDate dataFim) throws Exception {
        try {
            return repository.obterQuantidadeEventos(dataInicio, dataFim);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Integer obterQuantidadeEventosHoje() throws Exception {
        try {
            return repository.obterQuantidadeEventosHoje();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Evento> obterEventosAgendadosRecentemente() throws Exception {
        try {
            return repository.obterEventosAgendadosRecentemente();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Integer obterQuantidadeEventosRecorrentes(LocalDate dataInicio, LocalDate dataFim) {
        return 0;
        /*return eventoService.obterQuantidadeEventosRecorrentes(dataInicio, dataFim);*/
    }

    public String obterEventosNoDia(Local local, LocalDate data) throws Exception {
        // Obtenha os eventos já cadastrados para o local e data
        List<Evento> eventos = repository.obterEventosNoDia(local.getId(), data);

        LocalTime abertura = local.getHorarioAbertura();
        LocalTime fechamento = local.getHorarioFechamento();

        // Lista para armazenar intervalos livres
        List<String> horariosLivres = new ArrayList<>();

        // Ordena os eventos por horário de início
        eventos.sort(Comparator.comparing(Evento::getHorainicio));

        // Hora atual começa no horário de abertura
        LocalTime horaAtual = abertura;

        for (Evento evento : eventos) {
            LocalTime inicioEvento = evento.getHorainicio();
            LocalTime fimEvento = evento.getHorafim();

            // Adiciona intervalos de 1 em 1 hora enquanto houver espaço entre horaAtual e início do evento
            while (horaAtual.isBefore(inicioEvento) && horaAtual.plusHours(1).isBefore(inicioEvento) || horaAtual.plusHours(1).equals(inicioEvento)) {
                LocalTime proximaHora = horaAtual.plusHours(1);
                horariosLivres.add(formatarIntervalo(horaAtual, proximaHora));
                horaAtual = proximaHora;
            }

            // Atualiza a horaAtual para o fim do evento, se ela estiver antes
            if (horaAtual.isBefore(fimEvento)) {
                horaAtual = fimEvento;
            }
        }

        // Adiciona intervalos finais, de 1 em 1 hora, até o horário de fechamento
        while (horaAtual.isBefore(fechamento)) {
            LocalTime proximaHora = horaAtual.plusHours(1);
            if (proximaHora.isAfter(fechamento)) {
                proximaHora = fechamento;
            }
            horariosLivres.add(formatarIntervalo(horaAtual, proximaHora));
            horaAtual = proximaHora;
        }

        // Retorna os horários livres como uma string formatada
        return String.join("; ", horariosLivres);
    }

    // Método auxiliar para formatar o intervalo de tempo
    private String formatarIntervalo(LocalTime inicio, LocalTime fim) {
        return String.format("%s às %s", inicio, fim);
    }
}
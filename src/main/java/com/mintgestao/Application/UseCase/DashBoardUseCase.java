package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.ContasAReceberService;
import com.mintgestao.Application.Service.EventoService;
import com.mintgestao.Domain.DTO.DashBoard.DashBoardResponseDTO;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DashBoardUseCase {

    @Autowired
    LocalRepository localRepository;

    @Autowired
    ContasAReceberService contasAReceberService;

    @Autowired
    EventoService eventoService;

    public Double obterReceitaTotalPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            return contasAReceberService.obterReceitaTotalPorPeriodo(dataInicio, dataFim);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Integer obterQuantidadeEventosRecorrentes(LocalDate dataInicio, LocalDate dataFim) {
        try{
        return eventoService.obterQuantidadeEventosRecorrentes(dataInicio, dataFim);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Integer obterQuantidadeEventos(LocalDate dataInicio, LocalDate dataFim) throws Exception {
        try {
            return eventoService.obterQuantidadeEventos(dataInicio, dataFim);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Integer obterQuantidadeEventosHoje() throws Exception {
        try {
            return eventoService.obterQuantidadeEventosHoje();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Evento> obterEventosAgendadosRecentemente() throws Exception {
        try {
            return eventoService.obterEventosAgendadosRecentemente();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public DashBoardResponseDTO recuperarDashBoard(LocalDate dataInicio, LocalDate dataFim) throws Exception {
        try {
            return new DashBoardResponseDTO(
                    obterReceitaTotalPorPeriodo(dataInicio, dataFim),
                    obterQuantidadeEventosRecorrentes(dataInicio, dataFim),
                    obterQuantidadeEventos(dataInicio, dataFim),
                    obterQuantidadeEventosHoje(),
                    obterEventosAgendadosRecentemente()
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
package com.mintgestao.Application.UseCase.Evento;

import com.mintgestao.Domain.Entity.Evento;

import java.util.List;
import java.util.UUID;

public interface IEventoUseCase {
    List<Evento> obterTodosEventos() throws Exception;
    Evento obterEventoPorId(UUID id) throws Exception;
    Evento criarEvento(Evento evento) throws Exception;
    void atualizarEvento(UUID id, Evento evento) throws Exception;
    void excluirEvento(UUID id) throws Exception;
}

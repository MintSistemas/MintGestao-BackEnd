package com.mintgestao.Application.Service.Tema;

import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Entity.Tema;

import java.util.List;
import java.util.UUID;

public interface ITemaService {
    List<Tema> obterTodosTemas() throws Exception;
    Tema obterTemaPorId(UUID id) throws Exception;
    Tema obterTemaPorUsuario(UUID id) throws Exception;
    Tema criarTema(Tema tema) throws Exception;
    Boolean atualizarTema(UUID id, Tema tema) throws Exception;
    Boolean excluirTema(UUID id) throws Exception;
}

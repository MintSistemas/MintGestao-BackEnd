package com.mintgestao.Application.UseCase.Local;

import com.mintgestao.Domain.Entity.Local;

import java.util.List;
import java.util.UUID;

public interface ILocalUseCase {
    List<Local> obterTodosLocais() throws Exception;
    Local obterLocalPorId(UUID id) throws Exception;
    Local criarLocal(Local local) throws Exception;
    Boolean atualizarLocal(UUID id, Local local) throws Exception;
    Boolean excluirLocal(UUID id) throws Exception;
}

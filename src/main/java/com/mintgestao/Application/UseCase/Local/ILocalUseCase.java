package com.mintgestao.Application.UseCase.Local;

import com.mintgestao.Domain.Entity.Local;

import java.util.List;
import java.util.UUID;

public interface ILocalUseCase {
    List<Local> obterTodosLocais();
    Local obterLocalPorId(UUID id);
    Local criarLocal(Local local);
    Boolean atualizarLocal(UUID id, Local local);
    Boolean excluirLocal(UUID id);
}

package com.mintgestao.Application.UseCase.Filial;

import com.mintgestao.Domain.Entity.Filial;

import java.util.List;
import java.util.UUID;

public interface IFilialUseCase {
    List<Filial> obterTodosLocais();
    Filial obterFilialPorId(UUID id);
    Filial criarFilial(Filial filial);
    Boolean atualizarFilial(UUID id, Filial filial);
    Boolean excluirFilial(UUID id);
}

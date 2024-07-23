package com.mintgestao.Application.UseCase.Filial;

import com.mintgestao.Domain.Entity.Filial;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IFilialUseCase {
    CompletableFuture<List<Filial>> obterTodosLocais();
    CompletableFuture<Filial> obterFilialPorId(UUID id);
    CompletableFuture<Filial> criarFilial(Filial filial);
    CompletableFuture<Boolean> atualizarFilial(UUID id, Filial filial);
    CompletableFuture<Boolean> excluirFilial(UUID id);
}
package com.mintgestao.Application.UseCase.Local;

import com.mintgestao.Domain.Entity.Local;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ILocalUseCase {
    CompletableFuture<List<Local>> obterTodosLocais();
    CompletableFuture<Local> obterLocalPorId(UUID id);
    CompletableFuture<Local> criarLocal(Local local);
    CompletableFuture<Boolean> atualizarLocal(UUID id, Local local);
    CompletableFuture<Boolean> excluirLocal(UUID id);
}
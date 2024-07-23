package com.mintgestao.Application.UseCase.Local;

import com.mintgestao.Application.Service.Local.ILocalService;
import com.mintgestao.Domain.Entity.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class LocalUseCase implements ILocalUseCase {

    @Autowired
    private ILocalService localService;

    public LocalUseCase(ILocalService localService) {
        this.localService = localService;
    }

    @Override
    public CompletableFuture<List<Local>> obterTodosLocais() {
        return localService.obterTodosLocais();
    }

    @Override
    public CompletableFuture<Local> obterLocalPorId(UUID id) {
        return localService.obterLocalPorId(id);
    }

    @Override
    public CompletableFuture<Local> criarLocal(Local local) {
        return localService.criarLocal(local);
    }

    @Override
    public CompletableFuture<Boolean> atualizarLocal(UUID id, Local local) {
        return localService.atualizarLocal(id, local);
    }

    @Override
    public CompletableFuture<Boolean> excluirLocal(UUID id) {
        return localService.excluirLocal(id);
    }
}
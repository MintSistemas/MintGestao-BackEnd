package com.mintgestao.Application.UseCase.Filial;

import com.mintgestao.Application.Service.Filial.IFilialService;
import com.mintgestao.Domain.Entity.Filial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class FilialUseCase implements IFilialUseCase {

    @Autowired
    private IFilialService filialService;

    public FilialUseCase(IFilialService filialService) {
        this.filialService = filialService;
    }

    @Override
    public CompletableFuture<List<Filial>> obterTodosLocais() {
        return filialService.obterTodosLocais();
    }

    @Override
    public CompletableFuture<Filial> obterFilialPorId(UUID id) {
        return filialService.obterFilialPorId(id);
    }

    @Override
    public CompletableFuture<Filial> criarFilial(Filial filial) {
        return filialService.criarFilial(filial);
    }

    @Override
    public CompletableFuture<Boolean> atualizarFilial(UUID id, Filial filial) {
        return filialService.atualizarFilial(id, filial);
    }

    @Override
    public CompletableFuture<Boolean> excluirFilial(UUID id) {
        return filialService.excluirFilial(id);
    }
}
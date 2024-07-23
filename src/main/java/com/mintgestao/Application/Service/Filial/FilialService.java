package com.mintgestao.Application.Service.Filial;

import com.mintgestao.Domain.Entity.Filial;
import com.mintgestao.Infrastructure.Repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
public class FilialService implements IFilialService {

    @Autowired
    private FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    @Override
    public CompletableFuture<List<Filial>> obterTodosLocais() {
        return CompletableFuture.supplyAsync(() -> filialRepository.findAll(), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Filial> obterFilialPorId(UUID id) {
        return CompletableFuture.supplyAsync(() -> filialRepository.findById(id).orElse(null), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Filial> criarFilial(Filial filial) {
        return CompletableFuture.supplyAsync(() -> filialRepository.save(filial), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> atualizarFilial(UUID id, Filial filial) {
        return CompletableFuture.supplyAsync(() -> {
            if (filialRepository.existsById(id)) {
                filial.setId(id);
                filialRepository.save(filial);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> excluirFilial(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (filialRepository.existsById(id)) {
                filialRepository.deleteById(id);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }
}
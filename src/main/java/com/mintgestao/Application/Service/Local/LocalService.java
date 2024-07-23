package com.mintgestao.Application.Service.Local;

import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
public class LocalService implements ILocalService {

    @Autowired
    private LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public CompletableFuture<List<Local>> obterTodosLocais() {
        return CompletableFuture.supplyAsync(() -> localRepository.findAll(), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Local> obterLocalPorId(UUID id) {
        return CompletableFuture.supplyAsync(() -> localRepository.findById(id).orElse(null), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Local> criarLocal(Local local) {
        return CompletableFuture.supplyAsync(() -> localRepository.save(local), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> atualizarLocal(UUID id, Local local) {
        return CompletableFuture.supplyAsync(() -> {
            if (localRepository.existsById(id)) {
                local.setId(id);
                localRepository.save(local);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> excluirLocal(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (localRepository.existsById(id)) {
                localRepository.deleteById(id);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }
}
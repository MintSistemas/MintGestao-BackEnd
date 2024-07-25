package com.mintgestao.Application.Service.Local;

import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalService implements ILocalService {

    @Autowired
    private LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public List<Local> obterTodosLocais() {
        return localRepository.findAll();
    }

    @Override
    public Local obterLocalPorId(UUID id) {
        return localRepository.findById(id).orElse(null);
    }

    @Override
    public Local criarLocal(Local local) {
        return localRepository.save(local);
    }

    @Override
    public Boolean atualizarLocal(UUID id, Local local) {
        if (localRepository.existsById(id)) {
            local.setId(id);
            localRepository.save(local);
            return true;
        }
        return false;
    }

    @Override
    public Boolean excluirLocal(UUID id) {
        if (localRepository.existsById(id)) {
            localRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

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
    public List<Local> obterTodosLocais() throws Exception {
        try {
            return localRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Local obterLocalPorId(UUID id) throws Exception {
        try {
            return localRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Local criarLocal(Local local) throws Exception {
        try {
            return localRepository.save(local);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarLocal(UUID id, Local local) throws Exception {
       try {
            if (localRepository.existsById(id)) {
                local.setId(id);
                localRepository.save(local);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirLocal(UUID id) throws Exception {
        try {
            localRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

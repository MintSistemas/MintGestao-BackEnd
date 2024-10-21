package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusLocal;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalService extends ServiceBase<Local, LocalRepository> {

    @Autowired
    public LocalService(LocalRepository repository) {
        super(repository);
    }

    public Local mudarStatus(UUID id, EnumStatusLocal novoStatus) {
        Local local = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado: " + id));

        if (local.getStatus() == novoStatus) {
            throw new IllegalArgumentException("O status do Local já é " + novoStatus);
        }

        local.setStatus(novoStatus);
        return repository.save(local);
    }

    public List<Local> mudarStatusDeVarios(List<UUID> ids, EnumStatusLocal novoStatus) throws Exception {
        try {
            List<Local> locais = repository.findAllById(ids);

        if (locais.isEmpty()) {
            throw new EntityNotFoundException("Nenhum Local encontrado para os IDs fornecidos.");
        }

        for (Local local : locais) {
            if (local.getStatus() == novoStatus) {
                throw new Exception("O status do Local já é " + novoStatus);
            }
            local.setStatus(novoStatus);
        }

        return repository.saveAll(locais);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

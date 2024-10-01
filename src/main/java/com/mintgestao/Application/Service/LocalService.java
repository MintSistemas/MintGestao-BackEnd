package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusLocal;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocalService extends ServiceBase<Local, LocalRepository> {

    @Autowired
    public LocalService(LocalRepository repository) {
        super(repository);
    }

    public Local ativar(UUID id) throws Exception {
        try {
            Local local = repository.findById(id).orElseThrow();
            local.setStatus(EnumStatusLocal.Ativo);
            return repository.save(local);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Local desativar(UUID id) throws Exception {
        try {
            Local local = repository.findById(id).orElseThrow();
            local.setStatus(EnumStatusLocal.Inativo);
            return repository.save(local);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

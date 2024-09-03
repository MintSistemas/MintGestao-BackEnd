package com.mintgestao.Application.Service.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public class ServiceBase<TClass, TRepository extends JpaRepository<TClass, UUID>> implements IServiceBase<TClass> {
    protected final TRepository repository;

    public ServiceBase(TRepository repository) {
        this.repository = repository;
    }

    public List<TClass> buscarTodos() throws Exception {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TClass buscarPorId(UUID id) throws Exception {
        try {
            return repository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TClass criar(TClass entity) throws Exception {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void atualizar(UUID id, TClass entity) throws Exception {
        try {
            if (!repository.existsById(id)) throw new Exception("Registro não encontrado");
            repository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void excluir(UUID id) throws Exception {
        try {
            if (!repository.existsById(id)) throw new Exception("Registro não encontrado");
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
package com.mintgestao.Application.UseCase.Infrastructure;

import com.mintgestao.Application.Service.Infrastructure.ServiceBase;

import java.util.List;
import java.util.UUID;

public class UseCaseBase<TClass> implements IUseCaseBase<TClass> {

    protected final ServiceBase<TClass, ?> service;

    public UseCaseBase(ServiceBase<TClass, ?> service) {
        this.service = service;
    }

    public List<TClass> buscarTodos() throws Exception {
        try {
            return service.buscarTodos();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TClass buscarPorId(UUID id) throws Exception {
        try {
            return service.buscarPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TClass criar(TClass entity) throws Exception {
        try {
            return service.criar(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void atualizar(UUID id, TClass entity) throws Exception {
        try {
            service.atualizar(id, entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void excluir(UUID id) throws Exception {
        try {
            service.excluir(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
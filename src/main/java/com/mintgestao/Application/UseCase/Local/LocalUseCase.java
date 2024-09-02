package com.mintgestao.Application.UseCase.Local;

import com.mintgestao.Application.Service.Local.ILocalService;
import com.mintgestao.Domain.Entity.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LocalUseCase implements ILocalUseCase {

    @Autowired
    private ILocalService localService;

    public LocalUseCase(ILocalService localService) {
        this.localService = localService;
    }

    @Override
    public List<Local> obterTodosLocais() throws Exception {
        try {
            return localService.obterTodosLocais();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Local obterLocalPorId(UUID id) throws Exception {
        try {
            return localService.obterLocalPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Local criarLocal(Local local) throws Exception {
        try {
            return localService.criarLocal(local);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarLocal(UUID id, Local local) throws Exception {
        try {
            return localService.atualizarLocal(id, local);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirLocal(UUID id) throws Exception {
        try {
            return localService.excluirLocal(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

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
    public List<Local> obterTodosLocais() {
        return localService.obterTodosLocais();
    }

    @Override
    public Local obterLocalPorId(UUID id) {
        return localService.obterLocalPorId(id);
    }

    @Override
    public Local criarLocal(Local local) {
        return localService.criarLocal(local);
    }

    @Override
    public Boolean atualizarLocal(UUID id, Local local) {
        return localService.atualizarLocal(id, local);
    }

    @Override
    public Boolean excluirLocal(UUID id) {
        return localService.excluirLocal(id);
    }
}

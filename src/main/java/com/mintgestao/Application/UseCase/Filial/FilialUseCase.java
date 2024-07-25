package com.mintgestao.Application.UseCase.Filial;

import com.mintgestao.Application.Service.Filial.IFilialService;
import com.mintgestao.Domain.Entity.Filial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class FilialUseCase implements IFilialUseCase {

    @Autowired
    private IFilialService filialService;

    public FilialUseCase(IFilialService filialService) {
        this.filialService = filialService;
    }

    @Override
    public List<Filial> obterTodosLocais() {
        return filialService.obterTodosLocais();
    }

    @Override
    public Filial obterFilialPorId(UUID id) {
        return filialService.obterFilialPorId(id);
    }

    @Override
    public Filial criarFilial(Filial filial) {
        return filialService.criarFilial(filial);
    }

    @Override
    public Boolean atualizarFilial(UUID id, Filial filial) {
        return filialService.atualizarFilial(id, filial);
    }

    @Override
    public Boolean excluirFilial(UUID id) {
        return filialService.excluirFilial(id);
    }
}

package com.mintgestao.Application.UseCase.Empresa;

import com.mintgestao.Application.Service.Empresa.IEmpresaService;
import com.mintgestao.Domain.Entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmpresaUseCase implements IEmpresaUseCase {

    @Autowired
    private IEmpresaService empresaService;

    public EmpresaUseCase(IEmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Override
    public List<Empresa> obterTodosLocais() {
        return empresaService.obterTodosLocais();
    }

    @Override
    public Empresa obterEmpresaPorId(UUID id) {
        return empresaService.obterEmpresaPorId(id);
    }

    @Override
    public Empresa criarEmpresa(Empresa empresa) {
        return empresaService.criarEmpresa(empresa);
    }

    @Override
    public Boolean atualizarEmpresa(UUID id, Empresa empresa) {
        return empresaService.atualizarEmpresa(id, empresa);
    }

    @Override
    public Boolean excluirEmpresa(UUID id) {
        return empresaService.excluirEmpresa(id);
    }
}

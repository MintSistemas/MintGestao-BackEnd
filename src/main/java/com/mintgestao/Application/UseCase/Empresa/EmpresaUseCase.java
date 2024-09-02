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
    public List<Empresa> obterTodasEmpresas() throws Exception {
        try {
            return empresaService.obterTodasEmpresas();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empresa obterEmpresaPorId(UUID id) throws Exception {
        try {
            return empresaService.obterEmpresaPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empresa criarEmpresa(Empresa empresa) throws Exception {
        try {
            return empresaService.criarEmpresa(empresa);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarEmpresa(UUID id, Empresa empresa) throws Exception {
        try {
            return empresaService.atualizarEmpresa(id, empresa);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirEmpresa(UUID id) throws Exception {
        try {
            return empresaService.excluirEmpresa(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

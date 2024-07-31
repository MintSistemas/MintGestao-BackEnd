package com.mintgestao.Application.Service.Empresa;

import com.mintgestao.Domain.Entity.Empresa;
import com.mintgestao.Infrastructure.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public List<Empresa> obterTodosLocais() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa obterEmpresaPorId(UUID id) {
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public Empresa criarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Boolean atualizarEmpresa(UUID id, Empresa empresa) {
        if (empresaRepository.existsById(id)) {
            empresa.setId(id);
            empresaRepository.save(empresa);
            return true;
        }
        return false;
    }

    @Override
    public Boolean excluirEmpresa(UUID id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
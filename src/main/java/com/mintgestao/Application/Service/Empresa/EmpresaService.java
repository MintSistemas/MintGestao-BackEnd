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
    public List<Empresa> obterTodasEmpresas() throws Exception {
        try {
            return empresaRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empresa obterEmpresaPorId(UUID id) throws Exception {
        try {
            return empresaRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empresa criarEmpresa(Empresa empresa) throws Exception {
        try {
            return empresaRepository.save(empresa);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarEmpresa(UUID id, Empresa empresa) throws Exception {
       try {
            if (empresaRepository.existsById(id)) {
                empresa.setId(id);
                empresaRepository.save(empresa);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirEmpresa(UUID id) throws Exception {
        try {
            empresaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Empresa;
import com.mintgestao.Infrastructure.Repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService extends ServiceBase<Empresa, EmpresaRepository> {

    public EmpresaService(EmpresaRepository repository) {
        super(repository);
    }
}
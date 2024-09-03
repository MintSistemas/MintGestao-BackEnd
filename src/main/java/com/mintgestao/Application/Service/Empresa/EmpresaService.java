package com.mintgestao.Application.Service.Empresa;

import com.mintgestao.Application.Service.Infrastructure.ServiceBase;
import com.mintgestao.Domain.Entity.Empresa;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Infrastructure.Repository.EmpresaRepository;
import com.mintgestao.Infrastructure.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaService extends ServiceBase<Empresa, EmpresaRepository> {

    public EmpresaService(EmpresaRepository repository) {
        super(repository);
    }
}
package com.mintgestao.Application.Service.Cliente;

import com.mintgestao.Application.Service.Infrastructure.ServiceBase;
import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Domain.Entity.Empresa;
import com.mintgestao.Infrastructure.Repository.ClienteRepository;
import com.mintgestao.Infrastructure.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService extends ServiceBase<Cliente, ClienteRepository> {

    public ClienteService(ClienteRepository repository) {
        super(repository);
    }
}

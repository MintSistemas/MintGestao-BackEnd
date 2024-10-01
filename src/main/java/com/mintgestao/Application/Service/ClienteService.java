package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Infrastructure.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends ServiceBase<Cliente, ClienteRepository> {

    public ClienteService(ClienteRepository repository) {
        super(repository);
    }
}

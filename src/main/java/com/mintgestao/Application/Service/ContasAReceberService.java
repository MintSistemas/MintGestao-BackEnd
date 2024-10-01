package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Domain.Entity.ContasAReceber;
import com.mintgestao.Infrastructure.Repository.ClienteRepository;
import com.mintgestao.Infrastructure.Repository.ContasAReceberRepository;
import org.springframework.stereotype.Service;

@Service
public class ContasAReceberService extends ServiceBase<ContasAReceber, ContasAReceberRepository> {

    public ContasAReceberService(ContasAReceberRepository repository) {
        super(repository);
    }
}

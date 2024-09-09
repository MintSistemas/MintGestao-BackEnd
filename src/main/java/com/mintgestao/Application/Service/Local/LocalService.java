package com.mintgestao.Application.Service.Local;

import com.mintgestao.Application.Service.Infrastructure.ServiceBase;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalService extends ServiceBase<Local, LocalRepository> {

    @Autowired
    public LocalService(LocalRepository repository) {
        super(repository);
    }
}

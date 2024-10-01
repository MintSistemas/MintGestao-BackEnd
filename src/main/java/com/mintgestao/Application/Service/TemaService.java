package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Tema;
import com.mintgestao.Infrastructure.Repository.TemaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TemaService extends ServiceBase<Tema, TemaRepository> {

    public TemaService(TemaRepository repository) {
        super(repository);
    }

    public Tema obterTemaPorUsuario(UUID id) throws Exception {
        try {
            return repository.findByUsuarioId(id).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Tema criar(Tema tema) throws Exception {
        try {
            repository.findByUsuarioId(tema.getUsuario().getId()).ifPresent(t -> {
                tema.setId(t.getId());
            });
            return repository.save(tema);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

package com.mintgestao.Application.Service.Tema;

import com.mintgestao.Domain.Entity.Tema;
import com.mintgestao.Infrastructure.Repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TemaService implements ITemaService {
    @Autowired
    private final TemaRepository temaRepository;

    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @Override
    public List<Tema> obterTodosTemas() throws Exception {
        try {
            return temaRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Tema obterTemaPorId(UUID id) throws Exception {
        try {
            return temaRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Tema obterTemaPorUsuario(UUID id) throws Exception {
        try {
            return temaRepository.findByUsuarioId(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Tema criarTema(Tema tema) throws Exception {
        try {
            return temaRepository.save(tema);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarTema(UUID id, Tema tema) throws Exception {
        try {
            temaRepository.findById(id).orElseThrow();
            tema.setId(id);
            temaRepository.save(tema);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirTema(UUID id) throws Exception {
        try {
            temaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

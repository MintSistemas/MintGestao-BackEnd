package com.mintgestao.Application.UseCase.Tema;

import com.mintgestao.Application.Service.Tema.ITemaService;
import com.mintgestao.Domain.Entity.Tema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TemaUseCase implements ITemaUseCase {
    @Autowired
    private ITemaService temaService;

    public TemaUseCase(ITemaService temaService) {
        this.temaService = temaService;
    }

    @Override
    public List<Tema> obterTodosTemas() throws Exception {
        try {
            return temaService.obterTodosTemas();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Tema obterTemaPorId(UUID id) throws Exception {
        try {
            return temaService.obterTemaPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Tema obterTemaPorUsuario(UUID id) throws Exception {
        try {
            return temaService.obterTemaPorUsuario(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Tema criarTema(Tema tema) throws Exception {
        try {
            return temaService.criarTema(tema);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarTema(UUID id, Tema tema) throws Exception {
        try {
            return temaService.atualizarTema(id, tema);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirTema(UUID id) throws Exception {
        try {
            return temaService.excluirTema(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
package com.mintgestao.Application.Service.Filial;

import com.mintgestao.Domain.Entity.Filial;

import java.util.List;
import java.util.UUID;

public interface IFilialService {
    List<Filial> obterTodosLocais();
    Filial obterFilialPorId(UUID id);
    Filial criarFilial(Filial filial);
    Boolean atualizarFilial(UUID id, Filial filial);
    Boolean excluirFilial(UUID id);
}

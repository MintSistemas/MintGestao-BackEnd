package com.mintgestao.Application.Service.Base;

import java.util.List;
import java.util.UUID;

public interface IServiceBase <TClass> {
    List<TClass> buscarTodos() throws Exception;
    TClass buscarPorId(UUID id) throws Exception;
    TClass criar(TClass entity) throws Exception;
    void atualizar(UUID id, TClass entity) throws Exception;
    void excluir(UUID id) throws Exception;
}

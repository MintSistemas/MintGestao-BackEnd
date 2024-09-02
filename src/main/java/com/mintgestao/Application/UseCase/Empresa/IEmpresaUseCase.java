package com.mintgestao.Application.UseCase.Empresa;

import com.mintgestao.Domain.Entity.Empresa;

import java.util.List;
import java.util.UUID;

public interface IEmpresaUseCase {
    List<Empresa> obterTodasEmpresas() throws Exception;
    Empresa obterEmpresaPorId(UUID id) throws Exception;
    Empresa criarEmpresa(Empresa empresa) throws Exception;
    Boolean atualizarEmpresa(UUID id, Empresa empresa) throws Exception;
    Boolean excluirEmpresa(UUID id) throws Exception;
}

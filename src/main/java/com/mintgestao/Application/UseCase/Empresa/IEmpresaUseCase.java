package com.mintgestao.Application.UseCase.Empresa;

import com.mintgestao.Domain.Entity.Empresa;

import java.util.List;
import java.util.UUID;

public interface IEmpresaUseCase {
    List<Empresa> obterTodosLocais();
    Empresa obterEmpresaPorId(UUID id);
    Empresa criarEmpresa(Empresa empresa);
    Boolean atualizarEmpresa(UUID id, Empresa empresa);
    Boolean excluirEmpresa(UUID id);
}

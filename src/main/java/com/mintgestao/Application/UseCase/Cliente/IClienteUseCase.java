package com.mintgestao.Application.UseCase.Cliente;

import com.mintgestao.Domain.Entity.Cliente;

import java.util.List;
import java.util.UUID;

public interface IClienteUseCase {
    List<Cliente> obterTodosClientes();
    Cliente obterClientePorId(UUID id);
    Cliente criarCliente(Cliente cliente);
    Boolean atualizarCliente(UUID id, Cliente cliente);
    Boolean excluirCliente(UUID id);
}
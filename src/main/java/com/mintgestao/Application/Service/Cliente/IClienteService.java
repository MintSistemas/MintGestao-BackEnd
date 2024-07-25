package com.mintgestao.Application.Service.Cliente;

import com.mintgestao.Domain.Entity.Cliente;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IClienteService {
    List<Cliente> obterTodosClientes();
    Cliente obterClientePorId(UUID id);
    Cliente criarCliente(Cliente cliente);
    Boolean atualizarCliente(UUID id, Cliente cliente);
    Boolean excluirCliente(UUID id);
}
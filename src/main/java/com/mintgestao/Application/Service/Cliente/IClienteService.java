package com.mintgestao.Application.Service.Cliente;

import com.mintgestao.Domain.Entity.Cliente;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IClienteService {
    List<Cliente> obterTodosClientes() throws Exception;
    Cliente obterClientePorId(UUID id) throws Exception;
    Cliente criarCliente(Cliente cliente) throws Exception;
    Boolean atualizarCliente(UUID id, Cliente cliente) throws Exception;
    Boolean excluirCliente(UUID id) throws Exception;
}
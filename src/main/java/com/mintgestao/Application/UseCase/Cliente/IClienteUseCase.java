package com.mintgestao.Application.UseCase.Cliente;

import com.mintgestao.Domain.Entity.Cliente;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IClienteUseCase {
    CompletableFuture<List<Cliente>> obterTodosClientes();
    CompletableFuture<Cliente> obterClientePorId(UUID id);
    CompletableFuture<Cliente> criarCliente(Cliente cliente);
    CompletableFuture<Boolean> atualizarCliente(UUID id, Cliente cliente);
    CompletableFuture<Boolean> excluirCliente(UUID id);
}
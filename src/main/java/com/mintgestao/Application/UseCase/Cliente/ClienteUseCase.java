package com.mintgestao.Application.UseCase.Cliente;

import com.mintgestao.Application.Service.Cliente.IClienteService;
import com.mintgestao.Domain.Entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ClienteUseCase implements IClienteUseCase {

    @Autowired
    private IClienteService clienteService;

    public ClienteUseCase(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public CompletableFuture<List<Cliente>> obterTodosClientes() {
        return clienteService.obterTodosClientes();
    }

    @Override
    public CompletableFuture<Cliente> obterClientePorId(UUID id) {
        return clienteService.obterClientePorId(id);
    }

    @Override
    public CompletableFuture<Cliente> criarCliente(Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    @Override
    public CompletableFuture<Boolean> atualizarCliente(UUID id, Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @Override
    public CompletableFuture<Boolean> excluirCliente(UUID id) {
        return clienteService.excluirCliente(id);
    }
}
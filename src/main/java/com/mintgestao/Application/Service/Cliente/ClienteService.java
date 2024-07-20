package com.mintgestao.Application.Service.Cliente;

import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Infrastructure.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public CompletableFuture<List<Cliente>> obterTodosClientes() {
        return CompletableFuture.supplyAsync(() -> clienteRepository.findAll(), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Cliente> obterClientePorId(UUID id) {
        return CompletableFuture.supplyAsync(() -> clienteRepository.findById(id).orElse(null), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Cliente> criarCliente(Cliente cliente) {
        return CompletableFuture.supplyAsync(() -> clienteRepository.save(cliente), Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> atualizarCliente(UUID id, Cliente cliente) {
        return CompletableFuture.supplyAsync(() -> {
            if (clienteRepository.existsById(id)) {
                cliente.setId(id);
                clienteRepository.save(cliente);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }

    @Override
    public CompletableFuture<Boolean> excluirCliente(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (clienteRepository.existsById(id)) {
                clienteRepository.deleteById(id);
                return true;
            }
            return false;
        }, Executors.newCachedThreadPool());
    }
}
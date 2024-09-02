package com.mintgestao.Application.UseCase.Cliente;

import com.mintgestao.Application.Service.Cliente.IClienteService;
import com.mintgestao.Domain.Entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ClienteUseCase implements IClienteUseCase {

    @Autowired
    private IClienteService clienteService;

    public ClienteUseCase(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public List<Cliente> obterTodosClientes() throws Exception {
        try {
            return clienteService.obterTodosClientes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente obterClientePorId(UUID id) throws Exception {
        try {
            return clienteService.obterClientePorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente criarCliente(Cliente cliente) throws Exception {
        try {
            return clienteService.criarCliente(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarCliente(UUID id, Cliente cliente) throws Exception {
        try {
            return clienteService.atualizarCliente(id, cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirCliente(UUID id) throws Exception {
        try {
            return clienteService.excluirCliente(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

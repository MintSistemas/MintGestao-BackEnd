package com.mintgestao.Application.Service.Cliente;

import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Infrastructure.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> obterTodosClientes() throws Exception {
        try {
            return clienteRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente obterClientePorId(UUID id) throws Exception {
        try {
            return clienteRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente criarCliente(Cliente cliente) throws Exception {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarCliente(UUID id, Cliente cliente) throws Exception {
        try {
            if (clienteRepository.existsById(id)) {
                cliente.setId(id);
                clienteRepository.save(cliente);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirCliente(UUID id) throws Exception {
        try {
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

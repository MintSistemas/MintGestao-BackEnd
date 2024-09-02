package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Cliente.IClienteUseCase;
import com.mintgestao.Domain.Entity.Cliente;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("gestao/cliente")
@Tag(name = "Cliente", description = "Endpoint responsavel pelo gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private IClienteUseCase clienteUseCase;

    public ClienteController(IClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @GetMapping
    public ResponseEntity obterTodosClientes() {
        try {
            List<Cliente> clientes = clienteUseCase.obterTodosClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obterClientePorId(@PathVariable UUID id) {
        try {
            Cliente cliente = clienteUseCase.obterClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity criarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteUseCase.criarCliente(cliente);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        try {
            clienteUseCase.atualizarCliente(id, cliente);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCliente(@PathVariable UUID id) {
        try {
            clienteUseCase.excluirCliente(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
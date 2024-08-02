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
    public ResponseEntity<List<Cliente>> obterTodosClientes() {
        List<Cliente> clientes = clienteUseCase.obterTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable UUID id) {
        Cliente cliente = clienteUseCase.obterClientePorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteUseCase.criarCliente(cliente);
        return ResponseEntity.created(null).body(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        Boolean sucesso = clienteUseCase.atualizarCliente(id, cliente);
        return sucesso ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable UUID id) {
        Boolean sucesso = clienteUseCase.excluirCliente(id);
        return sucesso ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Cliente.IClienteUseCase;
import com.mintgestao.Domain.Entity.Cliente;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
@CrossOrigin
@RestController
@RequestMapping("geral/clientes")
@Tag(name = "Clientes", description = "Endpoint responsavel pelo gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private IClienteUseCase clienteUseCase;

    public ClienteController(IClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Cliente>>> obterTodosClientes() {
        return clienteUseCase.obterTodosClientes()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Cliente>> obterClientePorId(@PathVariable UUID id) {
        return clienteUseCase.obterClientePorId(id)
                .thenApply(cliente -> cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Cliente>> criarCliente(@RequestBody Cliente cliente) {
        return clienteUseCase.criarCliente(cliente)
                .thenApply(novoCliente -> ResponseEntity.created(null).body(novoCliente));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> atualizarCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        return clienteUseCase.atualizarCliente(id, cliente)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> excluirCliente(@PathVariable UUID id) {
        return clienteUseCase.excluirCliente(id)
                .thenApply(result -> result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }
}
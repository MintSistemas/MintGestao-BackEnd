package com.mintgestao.Api.Controller;

import com.mintgestao.Domain.Entity.Cidade;
import com.mintgestao.Domain.Entity.Estado;
import com.mintgestao.Infrastructure.Repository.CidadeRepository;
import com.mintgestao.Infrastructure.Repository.EstadoRepository;
import com.mintgestao.Infrastructure.util.SincronizacaoLocalidadesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadeController {

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;

    public LocalidadeController(EstadoRepository estadoRepository, CidadeRepository cidadeRepository) {
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping("/estados")
    public List<Estado> getEstados() {
        return estadoRepository.findAll();
    }

    @GetMapping("/cidades")
    public List<Cidade> getCidadesPorEstado(@RequestParam Long estadoId) {
        return cidadeRepository.findByEstadoId(estadoId);
    }

    @GetMapping("/atualizarDados")
    public void atualizarDados() {
        SincronizacaoLocalidadesService sincronizacaoLocalidadesService = new SincronizacaoLocalidadesService(estadoRepository, cidadeRepository);
        sincronizacaoLocalidadesService.sincronizarLocalidades();
    }
}
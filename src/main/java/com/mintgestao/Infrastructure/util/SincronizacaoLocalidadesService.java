package com.mintgestao.Infrastructure.util;

import com.mintgestao.Domain.Entity.Bairro;
import com.mintgestao.Domain.Entity.Cidade;
import com.mintgestao.Domain.Entity.Estado;
import com.mintgestao.Infrastructure.Repository.BairroRepository;
import com.mintgestao.Infrastructure.Repository.CidadeRepository;
import com.mintgestao.Infrastructure.Repository.EstadoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

@Service
public class SincronizacaoLocalidadesService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final BairroRepository bairroRepository;

    public SincronizacaoLocalidadesService(EstadoRepository estadoRepository, CidadeRepository cidadeRepository, BairroRepository bairroRepository) {
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.bairroRepository = bairroRepository;
    }

//    @Scheduled(cron = "00 45 17 * * *") // Atualiza diariamente
    public void sincronizarDados() {
        atualizarEstados();
        atualizarCidades();
        atualizarBairros();
    }

    private void atualizarEstados() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        Estado[] estados = restTemplate.getForObject(url, Estado[].class);
        if (estados != null) {
            estadoRepository.saveAll(Arrays.asList(estados));
        }
    }

    private void atualizarCidades() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";
        Cidade[] cidades = restTemplate.getForObject(url, Cidade[].class);
        if (cidades != null) {
            cidadeRepository.saveAll(Arrays.asList(cidades));
        }
    }

    private void atualizarBairros() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/distritos";
        Bairro[] bairros = restTemplate.getForObject(url, Bairro[].class);
        if (bairros != null) {
            bairroRepository.saveAll(Arrays.asList(bairros));
        }
    }
}

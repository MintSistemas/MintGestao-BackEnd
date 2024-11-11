package com.mintgestao.Infrastructure.util;

import com.mintgestao.Domain.Entity.Cidade;
import com.mintgestao.Domain.Entity.Estado;
import com.mintgestao.Infrastructure.Repository.CidadeRepository;
import com.mintgestao.Infrastructure.Repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class SincronizacaoLocalidadesService {

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;

    @Autowired
    public SincronizacaoLocalidadesService(
            EstadoRepository estadoRepository,
            CidadeRepository cidadeRepository) {

        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    private final String URL_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private final String URL_CIDADES = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/{UF}/municipios";

    private final RestTemplate restTemplate = new RestTemplate();

    public void sincronizarLocalidades() {
        buscarEstados();
        buscarCidades();
    }

    @Transactional
    public void buscarEstados() {
        ResponseEntity<Estado[]> response = restTemplate.getForEntity(URL_ESTADOS, Estado[].class);
        Estado[] estados = response.getBody();

        if (estados != null) {
            estadoRepository.saveAll(Arrays.asList(estados));
        }
    }

    @Transactional
    public void buscarCidades() {
        List<Estado> estados = estadoRepository.findAll();

        for (Estado estado : estados) {
            String urlCidades = URL_CIDADES.replace("{UF}", estado.getSigla());
            ResponseEntity<Cidade[]> response = restTemplate.getForEntity(urlCidades, Cidade[].class);
            Cidade[] Cidades = response.getBody();

            if (Cidades != null) {
                for (Cidade Cidade : Cidades) {
                    Cidade.setEstado(estado);
                }
                cidadeRepository.saveAll(Arrays.asList(Cidades)); // Batch insert
            }
        }
    }
}
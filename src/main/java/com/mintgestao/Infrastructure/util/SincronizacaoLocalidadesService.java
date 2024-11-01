package com.mintgestao.Infrastructure.util;

import com.mintgestao.Domain.Entity.Bairro;
import com.mintgestao.Domain.Entity.Cidade;
import com.mintgestao.Domain.Entity.Estado;
import com.mintgestao.Infrastructure.Repository.BairroRepository;
import com.mintgestao.Infrastructure.Repository.CidadeRepository;
import com.mintgestao.Infrastructure.Repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SincronizacaoLocalidadesService {

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final BairroRepository bairroRepository;

    @Autowired
    public SincronizacaoLocalidadesService(
            EstadoRepository estadoRepository,
            CidadeRepository cidadeRepository,
            BairroRepository bairroRepository) {

        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.bairroRepository = bairroRepository;
    }

    private final String URL_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private final String URL_CIDADES = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/{UF}/municipios";
    private final String URL_BAIRROS = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/{codigo_municipio}/distritos";

    private final RestTemplate restTemplate = new RestTemplate();

    public void sincronizarLocalidades() {
        buscarEstados();
        buscarCidades();
        buscarBairros();
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

    @Transactional
    public void buscarBairros() {
        List<Cidade> Cidades = cidadeRepository.findAll();

        for (Cidade Cidade : Cidades) {
            String urlBairros = URL_BAIRROS.replace("{codigo_municipio}", Cidade.getId().toString());
            ResponseEntity<Bairro[]> response = restTemplate.getForEntity(urlBairros, Bairro[].class);
            Bairro[] bairros = response.getBody();

            if (bairros != null) {
                for (Bairro bairro : bairros) {
                    bairro.setCidade(Cidade);
                }
                bairroRepository.saveAll(Arrays.asList(bairros)); // Batch insert
            }
        }
    }
}
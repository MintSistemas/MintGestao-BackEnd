package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Entity.ImagemLocal;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusLocal;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocalService extends ServiceBase<Local, LocalRepository> {

    @Autowired
    public LocalService(LocalRepository repository) {
        super(repository);
    }

    @Autowired
    private ImagemLocalService imagemLocalService;

    @Autowired
    private EventoService eventoService;

    public List<Local> buscarUltimasReservas(UUID idUsuario) throws Exception {
        List<Evento> ultimosEventos = eventoService.buscarUltimasReservas(idUsuario);

        return ultimosEventos.stream()
                .map(Evento::getLocal)
                .distinct()
                .collect(Collectors.toList());
    }

    public Local mudarStatus(UUID id, EnumStatusLocal novoStatus) {
        Local local = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado: " + id));

        if (local.getStatus() == novoStatus) {
            throw new IllegalArgumentException("O status do Local já é " + novoStatus);
        }

        local.setStatus(novoStatus);
        return repository.save(local);
    }

    public List<Local> mudarStatusDeVarios(List<UUID> ids, EnumStatusLocal novoStatus) throws Exception {
        try {
            List<Local> locais = repository.findAllById(ids);

            if (locais.isEmpty()) {
                throw new EntityNotFoundException("Nenhum Local encontrado para os IDs fornecidos.");
            }

            for (Local local : locais) {
                if (local.getStatus() == novoStatus) {
                    throw new Exception("O status do Local já é " + novoStatus);
                }
                local.setStatus(novoStatus);
            }

            return repository.saveAll(locais);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Local criar(Local local) {
        local.setStatus(EnumStatusLocal.Ativo);
        List<ImagemLocal> imagens = local.getImagens();

        // Remove as imagens do local para evitar erro de referência cíclica
        local.setImagens(null);

        Local localSalvo = repository.save(local);

        if (imagens != null) {
            imagens.forEach(imagem -> {
                try {
                    imagemLocalService.salvar(imagem, localSalvo);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return localSalvo;
    }

    @Override
    @Transactional
    public void atualizar(UUID id, Local local) throws Exception {
        // Busca o local atual
        Local localAtual = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado: " + id));

        // Atualiza os dados do local
        local.setId(localAtual.getId());
        local.setStatus(localAtual.getStatus());
        local.setDataAlteracao(new Date());

        // Gerencia as imagens associadas
        List<ImagemLocal> imagensAtuais = localAtual.getImagens();
        List<ImagemLocal> novasImagens = local.getImagens();

        // Excluir imagens que não estão mais na nova lista
        if (imagensAtuais != null && novasImagens != null) {
            List<ImagemLocal> imagensParaExcluir = imagensAtuais.stream()
                    .filter(imagemAtual -> novasImagens.stream()
                            .noneMatch(novaImagem -> novaImagem.getId() != null && novaImagem.getId().equals(imagemAtual.getId())))
                    .collect(Collectors.toList());

            for (ImagemLocal imagemParaExcluir : imagensParaExcluir) {
                imagemLocalService.excluirPorId(imagemParaExcluir.getId());
            }
        }

        // Adiciona as novas imagens associando ao local
        if (novasImagens != null) {
            novasImagens.forEach(imagem -> imagem.setLocal(local));
        }
        local.setImagens(novasImagens);

        // Salva o local com as novas imagens
        repository.save(local);
    }

    public String verificaEventosDiaAtual(Local local, LocalDate diaFiltro) throws Exception {
        return eventoService.obterEventosNoDia(local, diaFiltro);
    }
}

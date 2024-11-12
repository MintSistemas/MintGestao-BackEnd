package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.ImagemLocal;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusLocal;
import com.mintgestao.Infrastructure.Repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalService extends ServiceBase<Local, LocalRepository> {

    @Autowired
    public LocalService(LocalRepository repository) {
        super(repository);
    }

    @Autowired
    private ImagemLocalService imagemLocalService;

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
                    imagemLocalService.salvarImagem(imagem, localSalvo);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return localSalvo;
    }

    @Override
    public void atualizar(UUID id, Local local) {
        Local localAtual = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado: " + id));

        local.setId(localAtual.getId());
        local.setStatus(localAtual.getStatus());
        local.setDataAlteracao(localAtual.getDataAlteracao());

        if (local.getImagens() != null) {
            local.getImagens().forEach(imagem -> {
                try {
                    imagemLocalService.salvarImagem(imagem, local);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        repository.save(local);
    }
}

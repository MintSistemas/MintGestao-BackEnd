package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.ImagemLocal;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Infrastructure.Repository.ImagemLocalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImagemLocalService extends ServiceBase<ImagemLocal, ImagemLocalRepository> {

    public ImagemLocalService(ImagemLocalRepository repository) {
        super(repository);
    }{
    }

    public void salvarImagem(ImagemLocal imagemLocal, Local local) throws Exception {
        try {
            imagemLocal.setLocal(local);
            repository.save(imagemLocal);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void excluirPorLocalId(UUID localId) throws Exception {
        try {
            repository.deleteAllByLocalId(localId);
        } catch (Exception e) {
            throw new Exception("Erro ao excluir imagens locais: " + e.getMessage(), e);
        }
    }
}

package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.ImagemLocal;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Infrastructure.Repository.ImagemLocalRepository;
import org.springframework.stereotype.Service;

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
}

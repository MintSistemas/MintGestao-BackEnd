package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.LocalService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.ImagemLocal;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class LocalUseCase extends UseCaseBase<Local> {

    public LocalUseCase(LocalService service) {
        super(service);
    }

    public Local ativar(UUID id) throws Exception {
        try{
            return ((LocalService) service).mudarStatus(id, EnumStatusLocal.Ativo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Local desativar(UUID id) throws Exception {
        try{
            return ((LocalService) service).mudarStatus(id, EnumStatusLocal.Inativo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ImagemLocal adicionarImagem(UUID localId, List<MultipartFile> imagens) throws IOException {
        try {
            return ((LocalService) service).adicionarImagem(localId, imagens);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public List<ImagemLocal> listarImagensPorLocal(UUID localId) {
        try {
            return ((LocalService) service).listarImagensPorLocal(localId);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void removerImagem(UUID imagemId) {
        try {
            ((LocalService) service).removerImagem(imagemId);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}

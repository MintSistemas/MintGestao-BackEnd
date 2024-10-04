package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.LocalService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusLocal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LocalUseCase extends UseCaseBase<Local> {

    public LocalUseCase(LocalService service) {
        super(service);
    }

    public List<Local> ativar(List<UUID> ids) throws Exception {
        try{
            return ((LocalService) service).mudarStatusDeVarios(ids, EnumStatusLocal.Ativo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Local> desativar(List<UUID> ids) throws Exception {
        try{
            return ((LocalService) service).mudarStatusDeVarios(ids, EnumStatusLocal.Inativo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

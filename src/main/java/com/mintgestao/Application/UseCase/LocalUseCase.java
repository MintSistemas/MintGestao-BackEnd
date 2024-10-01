package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.LocalService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Local;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocalUseCase extends UseCaseBase<Local> {

    public LocalUseCase(LocalService service) {
        super(service);
    }

    public Local ativar(UUID id) throws Exception {
        try{
            return ((LocalService) service).ativar(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Local desativar(UUID id) throws Exception {
        try{
            return ((LocalService) service).desativar(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

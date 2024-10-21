package com.mintgestao.Application.UseCase;

import com.mintgestao.Api.Controller.ContasAReceberController;
import com.mintgestao.Application.Service.ContasAReceberService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.ContasAReceber;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContasAReceberUseCase extends UseCaseBase<ContasAReceber> {

    public ContasAReceberUseCase(ContasAReceberService service) {
        super(service);
    }

    public ContasAReceber baixar(UUID idContasAReceber) throws Exception {
        try {
            ((ContasAReceberService) service).verificarStatusValidoAlteracao(idContasAReceber);
            return ((ContasAReceberService) service).baixar(idContasAReceber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void cancelarContasAReceber(UUID idContasAReceber) throws Exception {
        try {
            ((ContasAReceberService) service).verificarStatusValidoAlteracao(idContasAReceber);
            ((ContasAReceberService) service).cancelar(idContasAReceber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

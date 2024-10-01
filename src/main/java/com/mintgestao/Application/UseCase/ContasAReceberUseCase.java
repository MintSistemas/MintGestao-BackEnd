package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.ClienteService;
import com.mintgestao.Application.Service.ContasAReceberService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Domain.Entity.ContasAReceber;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Enum.EnumStatusContasAReceber;
import org.springframework.stereotype.Component;

@Component
public class ContasAReceberUseCase extends UseCaseBase<ContasAReceber>{

        public ContasAReceberUseCase(ContasAReceberService service) {
            super(service);
        }
}

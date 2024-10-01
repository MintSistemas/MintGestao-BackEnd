package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.ContasAReceber;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Enum.EnumStatusContasAReceber;
import com.mintgestao.Infrastructure.Repository.ContasAReceberRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContasAReceberService extends ServiceBase<ContasAReceber, ContasAReceberRepository> {

    public ContasAReceberService(ContasAReceberRepository repository) {
        super(repository);
    }

    public Long gerarProximoNumero() {
        return repository.findMaxNumero() + 1;
    }

    public ContasAReceber gerarContasAReceber(@Valid  Evento evento) throws Exception {
        try {
            ContasAReceber contasAReceber = new ContasAReceber();
            contasAReceber.setEvento(evento);
            contasAReceber.setCliente(evento.getCliente());
            contasAReceber.setLocal(evento.getLocal());
            contasAReceber.setDataevento(evento.getDataevento());
            contasAReceber.setValor(evento.getValortotal());
            contasAReceber.setStatus(EnumStatusContasAReceber.Aberto);
            contasAReceber.setNumero(gerarProximoNumero());
            contasAReceber.setDataalteracao(new Date());

            return criar(contasAReceber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

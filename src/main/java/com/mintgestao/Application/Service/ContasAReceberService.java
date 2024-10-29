package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.ContasAReceber;
import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Enum.EnumStatusContasAReceber;
import com.mintgestao.Infrastructure.Repository.ContasAReceberRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContasAReceberService extends ServiceBase<ContasAReceber, ContasAReceberRepository> {

    public ContasAReceberService(ContasAReceberRepository repository) {
        super(repository);
    }

    public Long gerarProximoNumero() {
        return repository.findMaxNumero() + 1;
    }

    public ContasAReceber gerarContasAReceber(@Valid Evento evento) throws Exception {
        try {
            ContasAReceber contasAReceber = new ContasAReceber();
            contasAReceber.setEvento(evento);
            contasAReceber.setUsuario(evento.getUsuario());
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

    public void cancelarContasAReceber(UUID idevento) throws Exception {
        try {
            List<ContasAReceber> contasAReceber = repository.findAllByEventoId(idevento);

            for (ContasAReceber c : contasAReceber) {
                c.setStatus(EnumStatusContasAReceber.Cancelado);
                c.setDataalteracao(new Date());
                c.setObservacao("Evento cancelado");
            }

            repository.saveAll(contasAReceber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ContasAReceber baixar(UUID idContasAReceber) throws Exception {
        try {
            ContasAReceber contasAReceber = repository.findById(idContasAReceber).orElseThrow(() -> new Exception("Conta a receber não encontrada"));
            contasAReceber.setStatus(EnumStatusContasAReceber.Pago);
            contasAReceber.setDatabaixa(LocalDate.now());
            return repository.save(contasAReceber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ContasAReceber cancelar(UUID idContasAReceber) throws Exception {
        try {
            ContasAReceber contasAReceber = repository.findById(idContasAReceber).orElseThrow(() -> new Exception("Conta a receber não encontrada"));
            contasAReceber.setStatus(EnumStatusContasAReceber.Cancelado);
            contasAReceber.setDataalteracao(new Date());
            contasAReceber.setObservacao("Evento cancelado");
            return repository.save(contasAReceber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void verificarStatusValidoAlteracao(UUID idContasAReceber) throws Exception {
        ContasAReceber contasAReceber = repository.findById(idContasAReceber).orElseThrow(() -> new Exception("Conta a receber não encontrada"));
        if (contasAReceber.getStatus() != EnumStatusContasAReceber.Aberto) {
            throw new Exception("Status inválido para alteração");
        }
    }

    public void atualizarReceberComBaseNoEvento(Evento evento) throws Exception {
        try {
            List<ContasAReceber> contasAReceber = repository.findAllByEventoId(evento.getId());
            for (ContasAReceber c : contasAReceber) {
                c.setValor(evento.getValortotal());
                c.setDataevento(evento.getDataevento());
                c.setLocal(evento.getLocal());
                c.setUsuario(evento.getUsuario());
                c.setDataalteracao(new Date());
            }
            repository.saveAll(contasAReceber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

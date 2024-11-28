package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.LocalService;
import com.mintgestao.Application.UseCase.Base.UseCaseBase;
import com.mintgestao.Domain.DTO.Local.LocalResponseDTO;
import com.mintgestao.Domain.Entity.ImagemLocal;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Enum.EnumStatusLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<Local> buscarUltimasReservas(UUID idUsuario) throws Exception {
        try {
            return ((LocalService) service).buscarUltimasReservas(idUsuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String verificaEventosDiaAtual(Local local, LocalDate diaFiltro) throws Exception {
        try {
            return ((LocalService) service).verificaEventosDiaAtual(local, diaFiltro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public LocalResponseDTO buscarLocalCardApp(UUID id, LocalDate diaFiltro) throws Exception {
        try {
            Local local = service.buscarPorId(id);

            String eventosDia = this.verificaEventosDiaAtual(local, diaFiltro);

            return new LocalResponseDTO(
                    local.getId().toString(),
                    local.getNome(),
                    local.getStatus().toString(),
                    local.getCep(),
                    local.getEstado(),
                    local.getCidade(),
                    local.getBairro(),
                    local.getRua(),
                    local.getDiasFuncionamento(),
                    local.getComplemento(),
                    local.getHorarioAbertura(),
                    local.getHorarioFechamento(),
                    local.getObservacao(),
                    local.getValorHora(),
                    local.getImagens(),
                    local.getDataAlteracao(),
                    local.getDiasFuncionamentoList().stream().map(Enum::toString).toList(),
                    eventosDia
            );
        } catch (Exception e) {
            throw new Exception("Erro ao buscar os locais: " + e.getMessage());
        }
    }

    public Local desativar(UUID id) throws Exception {
        try{
            return ((LocalService) service).mudarStatus(id, EnumStatusLocal.Inativo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Local> buscarLocaisApp() throws Exception {
        try {
            List<Local> locais = service.buscarTodos();

            return locais.stream()
                    .filter(local -> local.getStatus().equals(EnumStatusLocal.Ativo))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Local> filtrarLocais(String nome, String data, String horaInicio, String horaFim, String estado, String cidade) throws Exception {
        try {
            return ((LocalService) service).filtrarLocais(nome, data, horaInicio, horaFim, estado, cidade);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

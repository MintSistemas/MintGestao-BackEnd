package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Evento;
import com.mintgestao.Domain.Enum.EnumStatusEvento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    @Query("SELECT e FROM Evento e WHERE e.local.id = :id AND e.status in (1, 2)")
    List<Evento> findByLocalId(UUID id);

    @Query("SELECT e FROM Evento e WHERE e.dataevento = :dataevento AND e.local.id = :id AND e.status in (1, 2)")
    List<Evento> buscarPorDataEventoEIdLocal(@NotNull(message = "Data é obrigatório") @Future(message = "Não é possível cadastrar um evento com a data menor que a atual") LocalDate dataevento, UUID id);

    @Query("SELECT COALESCE(MAX(e.numero), 0) FROM Evento e")
    public Long findMaxNumero();

    @Query("SELECT e FROM Evento e WHERE e.status in ( 1, 2) and e.dataAlteracao >= current_date")
    List<Evento> obterEventosAgendadosRecentemente();

    @Query("SELECT COUNT(e) FROM Evento e WHERE e.status = 1 and e.dataevento = current_date")
    Integer obterQuantidadeEventosHoje();

    @Query("SELECT COUNT(e) FROM Evento e WHERE e.status = 1 and e.dataevento BETWEEN ?1 AND ?2")
    Integer obterQuantidadeEventos(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT e FROM Evento e WHERE e.status = 1 and e.local.id = ?1 and e.dataevento = ?2")
    List<Evento> obterEventosNoDia(UUID idlocal, LocalDate data);

    @Query("SELECT e FROM Evento e WHERE e.status = 1 and e.usuario.id = ?1 ORDER BY e.dataAlteracao DESC limit 5")
    List<Evento> buscarUltimasReservas(UUID idUsuario);

    @Query("SELECT e FROM Evento e WHERE e.usuario.id = ?1 and e.dataevento = ?2")
    List<Evento> buscarEventosPorData(UUID idUsuario, LocalDate data);
}
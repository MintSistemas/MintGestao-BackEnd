package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Evento;
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
}

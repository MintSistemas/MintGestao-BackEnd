package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Evento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    List<Evento> findByLocalId(UUID id);

    List<Evento> findByDataeventoAndLocalId(@NotNull(message = "Data é obrigatório") @Future(message = "Não é possível cadastrar um evento com a data menor que a atual") LocalDate dataevento, UUID id);
}

package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.ContasAReceber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ContasAReceberRepository extends JpaRepository<ContasAReceber, UUID> {

    @Query("SELECT COALESCE(MAX(e.numero), 0) FROM ContasAReceber e")
    public Long findMaxNumero();

    public List<ContasAReceber> findAllByEventoId(UUID idevento);

    @Query("SELECT COALESCE(SUM(e.valor), 0) FROM ContasAReceber e WHERE e.status = 1 AND e.databaixa BETWEEN ?1 AND ?2")
    public Double obterReceitaTotalPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
}

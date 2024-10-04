package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.ContasAReceber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContasAReceberRepository extends JpaRepository<ContasAReceber, UUID> {

    @Query("SELECT COALESCE(MAX(e.numero), 0) FROM ContasAReceber e")
    public Long findMaxNumero();

    public List<ContasAReceber> findAllByEventoId(UUID idevento);
}

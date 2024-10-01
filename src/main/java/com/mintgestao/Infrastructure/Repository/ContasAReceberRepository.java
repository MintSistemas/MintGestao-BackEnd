package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.ContasAReceber;
import com.mintgestao.Domain.Entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContasAReceberRepository extends JpaRepository<ContasAReceber, UUID> {
}

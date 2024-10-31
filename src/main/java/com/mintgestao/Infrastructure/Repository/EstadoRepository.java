package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}

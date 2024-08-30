package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TemaRepository extends JpaRepository<Tema, UUID> {
    Optional<Tema> findByUsuarioId(UUID id);
}

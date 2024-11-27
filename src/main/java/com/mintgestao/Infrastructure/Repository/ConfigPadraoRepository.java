package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.ConfigPadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfigPadraoRepository extends JpaRepository<ConfigPadrao, UUID> {
    @Query("SELECT c FROM ConfigPadrao c WHERE c.usuario.id = :idUsuario")
    ConfigPadrao recuperarConfigPadrao(UUID idUsuario);
}

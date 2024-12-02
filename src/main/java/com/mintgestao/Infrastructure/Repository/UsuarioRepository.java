package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    List<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.idtenant = ?1")
    List<Usuario> findByTenant(Integer tenant);
}
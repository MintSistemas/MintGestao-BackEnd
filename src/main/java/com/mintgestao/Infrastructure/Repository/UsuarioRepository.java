package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Cliente;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
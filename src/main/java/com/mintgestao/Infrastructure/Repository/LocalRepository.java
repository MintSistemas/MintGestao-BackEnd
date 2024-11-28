package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocalRepository extends JpaRepository<Local, UUID> {

    @Query("SELECT l FROM Local l WHERE upper(l.nome) LIKE %?1% AND upper(l.cidade) LIKE %?2% AND upper(l.estado) LIKE %?3% AND l.status = 1")
    List<Local> filtrarLocais(String nome, String cidade, String estado) throws Exception;
}
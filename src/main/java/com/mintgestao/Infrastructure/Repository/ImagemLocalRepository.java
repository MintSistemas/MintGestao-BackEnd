package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.ImagemLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImagemLocalRepository extends JpaRepository<ImagemLocal, UUID> {
    List<ImagemLocal> findAllByLocalId(UUID localId);
}

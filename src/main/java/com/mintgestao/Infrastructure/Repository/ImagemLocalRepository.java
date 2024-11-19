package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.ImagemLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagemLocalRepository extends JpaRepository<ImagemLocal, UUID> {
    @Query("DELETE FROM ImagemLocal i WHERE i.local.id = :localId")
    void deleteAllByLocalId(UUID localId);
}


package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Filial;
import com.mintgestao.Domain.Entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilialRepository extends JpaRepository<Filial, UUID> {
}
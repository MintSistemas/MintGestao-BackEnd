package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocalRepository extends JpaRepository<Local, UUID> {
}
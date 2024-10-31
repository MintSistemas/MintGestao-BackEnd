package com.mintgestao.Infrastructure.Repository;

import com.mintgestao.Domain.Entity.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByCidadeId(Long cidadeId);
}

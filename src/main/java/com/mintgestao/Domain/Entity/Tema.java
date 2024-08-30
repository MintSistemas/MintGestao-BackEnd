package com.mintgestao.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String borderRadius;

    private boolean isDarkMode;

    private String primaryColor;

    private String secondaryColor;

    @OneToOne
    private Usuario usuario;
}
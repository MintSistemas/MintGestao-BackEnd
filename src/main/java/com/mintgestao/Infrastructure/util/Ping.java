package com.mintgestao.Infrastructure.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("ping")
@CrossOrigin("*")
@RestController
public class Ping {

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().build();
    }
}
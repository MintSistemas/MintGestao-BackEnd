package com.mintgestao;

import com.mintgestao.Infrastructure.util.Pong;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MintGestao {
    public static void main(String[] args) {
        SpringApplication.run(MintGestao.class, args);
        //Pong.pong();
    }
}

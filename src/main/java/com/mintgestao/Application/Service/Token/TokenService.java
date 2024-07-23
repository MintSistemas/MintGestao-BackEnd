package com.mintgestao.Application.Service.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String usuarioJson = new ObjectMapper().writeValueAsString(usuario);
            String token = JWT.create()
                    .withIssuer("MintSoftware")
                    .withClaim("Usuario", usuarioJson)
                    .withExpiresAt(getExpirationDateToken())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao criar token", e);
        }
    }

    public  Usuario validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("MintSoftware")
                    .build()
                    .verify(token);

            String usuarioJson = jwt.getClaim("Usuario").asString();
            Usuario usuario = new ObjectMapper().readValue(usuarioJson, Usuario.class);

            return usuario;
        } catch (JWTVerificationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao validar token", e);
        }
    }

    public String generateRefreshToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String usuarioJson = new ObjectMapper().writeValueAsString(usuario);
            String refreshToken = JWT.create()
                    .withIssuer("MintSoftware")
                    .withClaim("Usuario", usuarioJson)
                    .withExpiresAt(getExpirationDateRefreshToken())
                    .sign(algorithm);

            return refreshToken;
        } catch (JWTCreationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao criar token", e);
        }
    }

    public Usuario validateRefreshToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("MintSoftware")
                    .build()
                    .verify(refreshToken);

            String usuarioJson = jwt.getClaim("Usuario").asString();
            Usuario usuario = new ObjectMapper().readValue(usuarioJson, Usuario.class);

            return usuario;
        } catch (JWTVerificationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao validar token", e);
        }
    }

    private Instant getExpirationDateToken() {
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant getExpirationDateRefreshToken() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
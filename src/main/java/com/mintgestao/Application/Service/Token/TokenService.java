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
public class TokenService implements ITokenService {

    @Value("${api.security.token.secret}")

    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm Algoritimo = Algorithm.HMAC256(secret);
            String usuarioJson = new ObjectMapper().writeValueAsString(usuario);
            String token = JWT.create()
                    .withIssuer("MintSoftware")
                    .withClaim("Usuario", usuarioJson)
                    .withExpiresAt(getDataValidadeToken())
                    .sign(Algoritimo);

            return token;
        } catch (JWTCreationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao criar token", e);
        }
    }

    public Usuario validarToken(String token) throws Exception {
        try {
            Algorithm Algoritimo = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(Algoritimo)
                    .withIssuer("MintSoftware")
                    .build()
                    .verify(token);

            String usuarioJson = jwt.getClaim("Usuario").asString();
            Usuario usuario = new ObjectMapper().readValue(usuarioJson, Usuario.class);

            return usuario;
        } catch (JWTVerificationException | JsonProcessingException e) {
            throw new Exception("Erro ao validar token", e);
        }
    }

    public String gerarRefreshToken(Usuario usuario) {
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            String usuarioJson = new ObjectMapper().writeValueAsString(usuario);
            String refreshToken = JWT.create()
                    .withIssuer("MintSoftware")
                    .withClaim("Usuario", usuarioJson)
                    .withExpiresAt(getDataValidadeRefreshToken())
                    .sign(algoritimo);

            return refreshToken;
        } catch (JWTCreationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao criar token", e);
        }
    }

    public Usuario validarRefreshToken(String refreshToken) {
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            DecodedJWT TokenDescriptografado = JWT.require(algoritimo)
                    .withIssuer("MintSoftware")
                    .build()
                    .verify(refreshToken);

            String usuarioJson = TokenDescriptografado.getClaim("Usuario").asString();
            Usuario usuario = new ObjectMapper().readValue(usuarioJson, Usuario.class);

            return usuario;
        } catch (JWTVerificationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao validar token", e);
        }
    }

    public Instant getDataValidadeToken() {
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }

    public Instant getDataValidadeRefreshToken() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
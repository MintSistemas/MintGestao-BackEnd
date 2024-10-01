package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.AutenticacaoService;
import com.mintgestao.Domain.DTO.Login.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Login.LoginResponseDTO;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoUseCase {

    private AutenticacaoService service;

    public AutenticacaoUseCase(AutenticacaoService service) {
        this.service = service;
    }

    public LoginResponseDTO entrar(LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            return service.entrar(loginRequestDTO);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Boolean registrar(Usuario usuario) throws Exception {
        try {
            return service.registrar(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String atualizarToken(String refreshToken) throws Exception {
        try {
            return service.atualizarToken(refreshToken);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
package com.mintgestao.Application.UseCase.Autenticacao;

import com.mintgestao.Application.Service.Autenticacao.IAutenticacaoService;
import com.mintgestao.Application.Service.Cliente.IClienteService;
import com.mintgestao.Domain.DTO.Login.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Login.LoginResponseDTO;
import com.mintgestao.Domain.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoUseCase implements IAutenticacaoUseCase {

    @Autowired
    private IAutenticacaoService autenticacaoService;

    public AutenticacaoUseCase(IAutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    public LoginResponseDTO entrar(LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            return autenticacaoService.entrar(loginRequestDTO);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean registrar(Usuario usuario) throws Exception {
        try {
            return autenticacaoService.registrar(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String atualizarToken(String refreshToken) throws Exception {
        try {
            return autenticacaoService.atualizarToken(refreshToken);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

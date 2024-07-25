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
    public LoginResponseDTO entrar(LoginRequestDTO loginRequestDTO) {
        return autenticacaoService.entrar(loginRequestDTO);
    }

    @Override
    public Boolean registrar(Usuario usuario) {
        return autenticacaoService.registrar(usuario);
    }

    @Override
    public String atualizarToken(String refreshToken) {
        return autenticacaoService.atualizarToken(refreshToken);
    }
}

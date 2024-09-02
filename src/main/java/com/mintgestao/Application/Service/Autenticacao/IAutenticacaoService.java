package com.mintgestao.Application.Service.Autenticacao;

import com.mintgestao.Domain.DTO.Login.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Login.LoginResponseDTO;
import com.mintgestao.Domain.Entity.Usuario;

public interface IAutenticacaoService {
    LoginResponseDTO entrar(LoginRequestDTO loginRequestDTO) throws Exception;
    Boolean registrar(Usuario usuario) throws Exception;
    String atualizarToken(String refreshToken) throws Exception;
}

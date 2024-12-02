package com.mintgestao.Application.UseCase;

import com.mintgestao.Application.Service.AutenticacaoService;
import com.mintgestao.Domain.DTO.Autenticacao.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Autenticacao.LoginResponseDTO;
import com.mintgestao.Domain.DTO.DashBoard.DashBoardResponseDTO;
import com.mintgestao.Domain.DTO.Autenticacao.RegistroAppDTO;
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

    public Usuario registrar(Usuario usuario) throws Exception {
        try {
            usuario.setAtivo(true);
            return service.registrar(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario alterar(Usuario usuario) throws Exception {
        try {
            return service.alterar(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario registrarApp(RegistroAppDTO registroAppDTO) throws Exception {
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(registroAppDTO.nome());
            usuario.setEmail(registroAppDTO.email());
            usuario.setSenha(registroAppDTO.senha());
            usuario.setCidade(registroAppDTO.cidade());
            usuario.setBairro(registroAppDTO.bairro());
            usuario.setEndereco(registroAppDTO.endereco());
            usuario.setTelefone(registroAppDTO.telefone());
            usuario.setNumero(registroAppDTO.numero());
            usuario.setCpf(registroAppDTO.cpf());
            usuario.setDataNascimento(registroAppDTO.dataNascimento());
            usuario.setCep(registroAppDTO.cep());
            usuario.setUsaapp(registroAppDTO.usaapp());
            usuario.setIdtenant(-1);
            usuario.setAtivo(true);

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
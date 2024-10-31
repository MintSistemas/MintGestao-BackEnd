package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Token.TokenService;
import com.mintgestao.Domain.DTO.Autenticacao.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Autenticacao.LoginResponseDTO;
import com.mintgestao.Domain.DTO.DashBoard.DashBoardResponseDTO;
import com.mintgestao.Domain.Entity.Tema;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TemaService temaService;

    public LoginResponseDTO entrar(LoginRequestDTO data) throws Exception {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
            var refreshToken = tokenService.gerarRefreshToken((Usuario) auth.getPrincipal());

            Usuario usuario = (Usuario) repository.findByEmail(data.email());
            Tema tema = temaService.obterTemaPorUsuario(usuario.getId());

            return new LoginResponseDTO(usuario, token, refreshToken, tema);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario registrar(Usuario usuario) throws Exception {
        try {
            if (this.repository.findByEmail(usuario.getEmail()) != null) throw new Exception("Usuário já cadastrado");
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            return repository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String atualizarToken(String refreshToken) throws Exception {
        try {
            Usuario usuario = tokenService.validarRefreshToken(refreshToken);
            UserDetails user = repository.findByEmail(usuario.getEmail());
            return tokenService.gerarToken((Usuario) user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
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

import java.util.List;

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
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
            var refreshToken = tokenService.gerarRefreshToken((Usuario) auth.getPrincipal());

            Usuario usuario = (Usuario) repository.findByEmail(data.email()).stream().filter(Usuario::isAtivo).findFirst().get();
            Tema tema = temaService.obterTemaPorUsuario(usuario.getId());

            return new LoginResponseDTO(usuario, token, refreshToken, tema);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario registrar(Usuario usuario) throws Exception {
        try {
            verificaUsuarioAtivo(usuario.getEmail());

            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            return repository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario alterar(Usuario usuario) throws Exception {
        try {
            List<Usuario> usuarios = repository.findByEmail(usuario.getEmail());

            long countAtivos = usuarios.stream()
                    .filter(u -> !u.getId().equals(usuario.getId()) && u.isAtivo())
                    .count();

            if (countAtivos > 0) {
                throw new Exception("Já existe um usuário ativo cadastrado com este e-mail");
            }


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
            UserDetails user = repository.findByEmail(usuario.getEmail()).stream().filter(Usuario::isAtivo).findFirst().get();
            return tokenService.gerarToken((Usuario) user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void verificaUsuarioAtivo(String email) throws Exception {
        List<Usuario> usuarios = repository.findByEmail(email);
        if (usuarios.size() > 0) {
            for (Usuario u : usuarios) {
                if (u.isAtivo()) {
                    throw new Exception("Ja existe um usuario com esse email ativo");
                }
            }
        }
    }
}
package com.mintgestao.Api.Controller;

import com.mintgestao.Domain.DTO.Login.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Login.LoginResponseDTO;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import com.mintgestao.Application.Service.Token.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autenticacao")
@CrossOrigin
@Tag(name = "Autenticação", description = "Endpoint responsavel pela autenticação de usuários")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/entrar")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
            var refreshToken = tokenService.generateRefreshToken((Usuario) auth.getPrincipal());

            Usuario usuario = (Usuario) repository.findByEmail(data.email());
            return ResponseEntity.ok(new LoginResponseDTO(usuario, token, refreshToken));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid Usuario usuario) {
        if (this.repository.findByEmail(usuario.getEmail()) != null) return ResponseEntity.badRequest().build();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/atualizartoken")
    public ResponseEntity refresh(@RequestBody @Valid String refreshToken) {
        try {
            Usuario usuario = tokenService.validateRefreshToken(refreshToken);
            UserDetails user = repository.findByEmail(usuario.getEmail());
            return ResponseEntity.ok(tokenService.generateToken((Usuario) user));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
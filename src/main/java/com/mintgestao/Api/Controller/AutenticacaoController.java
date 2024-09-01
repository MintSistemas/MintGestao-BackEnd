package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.Autenticacao.IAutenticacaoUseCase;
import com.mintgestao.Application.UseCase.Cliente.IClienteUseCase;
import com.mintgestao.Domain.DTO.Login.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Login.LoginResponseDTO;
import com.mintgestao.Domain.DTO.RefreshToken.RefreshTokenDTO;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import com.mintgestao.Application.Service.Token.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autenticacao")
@CrossOrigin
@Tag(name = "Autenticação", description = "Endpoint responsavel pela autenticação de usuários")
public class AutenticacaoController {

    @Autowired
    private IAutenticacaoUseCase autenticacaoUseCase;

    public AutenticacaoController(IAutenticacaoUseCase autenticacaoUseCase) {
        this.autenticacaoUseCase = autenticacaoUseCase;
    }

    @PostMapping("/entrar")
    public ResponseEntity<LoginResponseDTO> entrar(@RequestBody @Valid LoginRequestDTO data) throws Exception {
        LoginResponseDTO dto = autenticacaoUseCase.entrar(data);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid Usuario usuario) {
        Boolean sucesso = autenticacaoUseCase.registrar(usuario);
        return sucesso ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/atualizartoken")
    public ResponseEntity atualizarToken(@RequestBody @Valid RefreshTokenDTO refreshTokenDTO) {
        String token = autenticacaoUseCase.atualizarToken(refreshTokenDTO.refreshToken());
        return token != null ? ResponseEntity.ok(token) : ResponseEntity.badRequest().build();
    }
}
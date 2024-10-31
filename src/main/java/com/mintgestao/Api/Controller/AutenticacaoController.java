package com.mintgestao.Api.Controller;

import com.mintgestao.Application.UseCase.AutenticacaoUseCase;
import com.mintgestao.Domain.DTO.Autenticacao.LoginRequestDTO;
import com.mintgestao.Domain.DTO.Autenticacao.LoginResponseDTO;
import com.mintgestao.Domain.DTO.DashBoard.DashBoardResponseDTO;
import com.mintgestao.Domain.DTO.Autenticacao.RegistroAppDTO;
import com.mintgestao.Domain.DTO.RefreshToken.RefreshTokenDTO;
import com.mintgestao.Domain.Entity.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autenticacao")
@CrossOrigin
@Tag(name = "Autenticação", description = "Endpoint responsavel pela autenticação de usuários")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoUseCase autenticacaoUseCase;

    public AutenticacaoController(AutenticacaoUseCase autenticacaoUseCase) {
        this.autenticacaoUseCase = autenticacaoUseCase;
    }

    @PostMapping("/entrar")
    public ResponseEntity entrar(@RequestBody @Valid LoginRequestDTO data) throws Exception {
        try {
            LoginResponseDTO loginResponseDTO = autenticacaoUseCase.entrar(data);
            return ResponseEntity.ok(loginResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/gestao/criar")
    public ResponseEntity registrar(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario usuarioAutenticado = autenticacaoUseCase.registrar(usuario);
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/app/criar")
    public ResponseEntity registrarApp(@RequestBody @Valid RegistroAppDTO dto) {
        try {
            Usuario usuarioAutenticado = autenticacaoUseCase.registrarApp(dto);
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/atualizartoken")
    public ResponseEntity atualizarToken(@RequestBody @Valid RefreshTokenDTO refreshTokenDTO) {
        try {
            String token = autenticacaoUseCase.atualizarToken(refreshTokenDTO.refreshToken());
            return token != null ? ResponseEntity.ok(token) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
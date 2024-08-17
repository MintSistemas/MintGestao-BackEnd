package com.mintgestao.Infrastructure.Security;

import com.mintgestao.Application.Service.Token.TokenService;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import com.mintgestao.Infrastructure.Tenant.TenantResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Filtro extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository userReposirory;

    @Autowired
    private TenantResolver TenantResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuperarToken(request);
        if (token != null) {
            Usuario usuario = null;
            try {
                usuario = tokenService.validarToken(token);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 403 Forbidden
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Acesso negado. " + e.getMessage() + "\"}");
            }
            UserDetails usuarioValidado = userReposirory.findByEmail(usuario.getEmail());

            var authentication = new UsernamePasswordAuthenticationToken(usuarioValidado, null, usuarioValidado.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            TenantResolver.setCurrentTenant(usuario.getIdtenant());
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("token");
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
}

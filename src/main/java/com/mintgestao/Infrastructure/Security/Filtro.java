package com.mintgestao.Infrastructure.Security;

import com.mintgestao.Application.Service.Token.TokenService;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import com.mintgestao.Infrastructure.Tenant.TenantIdentifierResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private TenantIdentifierResolver TenantIdentifierResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = retrieveToken(request);
        if (token != null) {
            Usuario usuario = tokenService.validateToken(token);
            UserDetails usuarioValidado = userReposirory.findByEmail(usuario.getEmail());

            var authentication = new UsernamePasswordAuthenticationToken(usuarioValidado, null, usuarioValidado.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            TenantIdentifierResolver.setCurrentTenant(usuario.getIdtenant());
        }
        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        String authHeader = request.getHeader("token");
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
}

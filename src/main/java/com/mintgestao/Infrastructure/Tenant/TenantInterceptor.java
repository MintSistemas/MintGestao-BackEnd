package com.mintgestao.Infrastructure.Tenant;

import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Application.Service.Token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Autowired
    private com.mintgestao.Infrastructure.Tenant.TenantIdentifierResolver TenantIdentifierResolver;

    @Autowired
    private TokenService TokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = retrieveToken(request);
        if (token == null) return true;
        Usuario usuario = TokenService.validateToken(token);

        if (usuario.getIdtenant() != null) {
            TenantIdentifierResolver.setCurrentTenant(usuario.getIdtenant());
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantIdentifierResolver.clear();
    }

    private String retrieveToken(HttpServletRequest request) {
        String authHeader = request.getHeader("token");
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
}
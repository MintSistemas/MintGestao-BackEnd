package com.mintgestao.Api.Interceptor;

import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Application.Service.Token.TokenService;
import com.mintgestao.Infrastructure.Tenant.TenantResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Autowired
    private TenantResolver tenantResolver;

    @Autowired
    private TokenService TokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String token = recuperarToken(request);
            if (token == null) return true;
            Usuario usuario = TokenService.validarToken(token);
            if (usuario.getIdtenant() != null) {
                tenantResolver.setCurrentTenant(usuario.getIdtenant());
            }
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().write(e.getMessage());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        tenantResolver.clear();
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) return null;
        return token.replace("Bearer ", "");
    }
}
package com.mintgestao.Application.Service;

import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return usuariosRepository.findByEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}

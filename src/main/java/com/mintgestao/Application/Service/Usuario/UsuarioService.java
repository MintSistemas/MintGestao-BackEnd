package com.mintgestao.Application.Service.Usuario;

import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obterTodosUsuarios() throws Exception {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario obterUsuarioPorId(UUID id) throws Exception {
        try {
            return usuarioRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) throws Exception {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean atualizarUsuario(UUID id, Usuario usuario) throws Exception {
        try {
            if (usuarioRepository.existsById(id)) {
                usuario.setId(id);
                usuarioRepository.save(usuario);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean excluirUsuario(UUID id) throws Exception {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
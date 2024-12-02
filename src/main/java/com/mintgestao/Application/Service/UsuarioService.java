package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService extends ServiceBase<Usuario, UsuarioRepository> {
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

    @Override
    public void atualizar(UUID id, Usuario usuario) throws Exception {
        try {
            if (!repository.existsById(id)) throw new Exception("Registro não encontrado");

            this.verificaUsuarioAtivo(id, usuario.getEmail());

            usuario.setId(id);
            repository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void verificaUsuarioAtivo(UUID idUsuario, String email) throws Exception {
        // Busca todos os usuários com o mesmo email
        List<Usuario> usuarios = repository.findByEmail(email);

        // Verifica se existe mais de um usuário ativo com o mesmo email
        long countAtivos = usuarios.stream()
                .filter(usuario -> !usuario.getId().equals(idUsuario) && usuario.isAtivo())
                .count();

        // Lança exceção se houver mais de um ativo
        if (countAtivos > 0) {
            throw new Exception("Já existe um usuário ativo cadastrado com este e-mail");
        }
    }

    public List<Usuario> buscarTodosPorTenant(Integer idtenant) throws Exception {
        try {
            return repository.findByTenant(idtenant);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
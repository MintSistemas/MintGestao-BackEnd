package com.mintgestao.Application.Service;

import com.mintgestao.Application.Service.Base.ServiceBase;
import com.mintgestao.Domain.Entity.ConfigPadrao;
import com.mintgestao.Domain.Entity.Local;
import com.mintgestao.Domain.Entity.Usuario;
import com.mintgestao.Infrastructure.Repository.ConfigPadraoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConfigPadraoService extends ServiceBase<ConfigPadrao, ConfigPadraoRepository> {

    @Autowired
    LocalService localService;

    @Autowired
    UsuarioService usuarioService;

    public ConfigPadraoService(ConfigPadraoRepository repository) {
        super(repository);
    }

    public ConfigPadrao recuperarConfigPadrao(UUID idUsuario) {
        return repository.recuperarConfigPadrao(idUsuario);
    }

    @Override
    public ConfigPadrao criar(ConfigPadrao configPadrao) throws Exception {
        Local local = localService.buscarPorId(configPadrao.getLocal().getId());
        Usuario usuario = usuarioService.buscarPorId(configPadrao.getUsuario().getId());
        configPadrao.setLocal(local);
        configPadrao.setUsuario(usuario);

        ConfigPadrao configPadraoExistente = recuperarConfigPadrao(configPadrao.getUsuario().getId());

        if (configPadraoExistente != null) {
            configPadrao.setId(configPadraoExistente.getId());
        }

        return repository.save(configPadrao);
    }
}
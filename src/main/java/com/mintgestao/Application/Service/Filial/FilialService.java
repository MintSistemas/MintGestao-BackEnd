package com.mintgestao.Application.Service.Filial;

import com.mintgestao.Domain.Entity.Filial;
import com.mintgestao.Infrastructure.Repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilialService implements IFilialService {

    @Autowired
    private FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    @Override
    public List<Filial> obterTodosLocais() {
        return filialRepository.findAll();
    }

    @Override
    public Filial obterFilialPorId(UUID id) {
        return filialRepository.findById(id).orElse(null);
    }

    @Override
    public Filial criarFilial(Filial filial) {
        return filialRepository.save(filial);
    }

    @Override
    public Boolean atualizarFilial(UUID id, Filial filial) {
        if (filialRepository.existsById(id)) {
            filial.setId(id);
            filialRepository.save(filial);
            return true;
        }
        return false;
    }

    @Override
    public Boolean excluirFilial(UUID id) {
        if (filialRepository.existsById(id)) {
            filialRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
package com.fernandoHidalgo.gestionBandas.backend.business.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoHidalgo.gestionBandas.backend.business.model.Bandas;
import com.fernandoHidalgo.gestionBandas.backend.integration.repositories.BandasRepository;

@Service
public class BandaServices {

    @Autowired
    private BandasRepository bandasRepository;

    @Transactional
    public Long create(Bandas banda) {
        if (banda.getId() != null) {
            throw new IllegalStateException("No se puede crear una banda con un ID no nulo");
        }
        return bandasRepository.save(banda).getId();
    }

    public Optional<Bandas> read(Long id) {
        return bandasRepository.findById(id);
    }

    @Transactional
    public void update(Bandas banda) {
        if (banda.getId() == null) {
            throw new IllegalStateException("La banda debe tener un ID para ser actualizada");
        }
        bandasRepository.save(banda);
    }

    @Transactional
    public void delete(Long id) {
        bandasRepository.deleteById(id);
    }

    public List<Bandas> getAll() {
        return bandasRepository.findAll();
    }
}

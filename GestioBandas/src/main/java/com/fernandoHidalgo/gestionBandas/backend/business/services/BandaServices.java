package com.fernandoHidalgo.gestionBandas.backend.business.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.fernandoHidalgo.gestionBandas.backend.business.model.Bandas;

@Service
public class BandaServices {

    private final TreeMap<Long, Bandas> BANDAS = new TreeMap<>();

    public BandaServices() {
       
    }

    public Long create(Bandas banda) {
        Long id = BANDAS.lastKey() + 1;
        banda.setId(id);
        BANDAS.put(banda.getId(), banda);
        return id;
    }

    public Optional<Bandas> read(Long id) {
        return Optional.ofNullable(BANDAS.get(id));
    }

    public List<Bandas> getAll() {
        return new ArrayList<>(BANDAS.values());
    }

    public void update(Bandas banda) {
        if (banda.getId() == null) {
            throw new IllegalArgumentException("La banda debe tener un ID para ser actualizada");
        }

        if (!BANDAS.containsKey(banda.getId())) {
            throw new IllegalArgumentException("No se puede encontrar la banda con el ID especificado para actualizar");
        }

        BANDAS.put(banda.getId(), banda);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("No se puede eliminar una banda con un ID nulo");
        }

        if (!BANDAS.containsKey(id)) {
            throw new IllegalArgumentException("No se puede encontrar la banda con el ID especificado para eliminar");
        }

        BANDAS.remove(id);
    }

   
}

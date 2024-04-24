package com.FernandoHidalgo.GestioBandas.Backend.presentation.restcontroller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.FernandoHidalgo.GestioBandas.Backend.business.model.Bandas;



@Service
public class BandaServices {

    private final TreeMap<Long, Bandas> BANDAS = new TreeMap<>();

    public BandaServices() {
        init();
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
    
    /*
     this.id = id;
		this.nom = nom;
		this.generoPrincipal = generoPrincipal;
		this.generosSecundarios = generosSecundarios;
		this.fechaFundacion = fechaFundacion;
		this.paisOrigen = paisOrigen;
		this.isActive = isActive;    
     */

    private void init() {
        // Crear listas de géneros secundarios
    	   Optional<List<String>> generosSecundariosBanda1 = Optional.of(Arrays.asList("Pop", "Rock and Roll"));
           Optional<List<String>> generosSecundariosBanda2 = Optional.of(Arrays.asList("Glam Rock", "Hard Rock"));
           Optional<List<String>> generosSecundariosBanda3 = Optional.of(Arrays.asList("Rock Alternativo", "Punk Rock"));
           Optional<List<String>> generosSecundariosBanda4 = Optional.of(Arrays.asList("Art Rock", "Psicodelia"));

        // Crear objetos LocalDate
        LocalDate fechaFundacionBanda1 = LocalDate.of(1960, 1, 1);
        LocalDate fechaFundacionBanda2 = LocalDate.of(1970, 7, 1);
        LocalDate fechaFundacionBanda3 = LocalDate.of(1987, 1, 1);
        LocalDate fechaFundacionBanda4 = LocalDate.of(1965, 1, 1);

        // Inicializar instancias de la clase Banda
        Bandas banda1 = new Bandas(1L, "The Beatles", "Rock", generosSecundariosBanda1, fechaFundacionBanda1, "Reino Unido", false);
        Bandas banda2 = new Bandas(2L, "Queen", "Rock", generosSecundariosBanda2, fechaFundacionBanda2, "Reino Unido", false);
        Bandas banda3 = new Bandas(3L, "Nirvana", "Grunge", generosSecundariosBanda3, fechaFundacionBanda3, "Estados Unidos", false);
        Bandas banda4 = new Bandas(4L, "Pink Floyd", "Rock Progresivo", generosSecundariosBanda4, fechaFundacionBanda4, "Reino Unio", false);

        // Utiliza las instancias de banda según sea necesario
        BANDAS.put(banda1.getId(), banda1);
        BANDAS.put(banda2.getId(), banda2);
        BANDAS.put(banda3.getId(), banda3);
        BANDAS.put(banda4.getId(), banda4);
    }

    public void update(Bandas banda) {
        // Implementar la lógica para actualizar una banda
    }

  
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalStateException("No se puede eliminar una banda con un ID nulo");
        }

        if (!BANDAS.containsKey(id)) {
            throw new IllegalStateException("La banda con ID " + id + " no existe. No se puede eliminar.");
        }

        BANDAS.remove(id);
    }
}

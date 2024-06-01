package com.FernandoHidalgo.GestionBandas.backend.integration.repositories;

import com.fernandoHidalgo.gestionBandas.backend.business.model.Bandas;
import com.fernandoHidalgo.gestionBandas.backend.integration.repositories.BandasRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class BandasRepositoryTest {

    @Autowired
    private BandasRepository bandasRepository;
    
    private Bandas banda1;
    private Bandas banda2;
    private Bandas banda3;
    private Bandas banda4;

    @BeforeEach
    void init() {
        initObjects();
    }

    @Test
    public void testFindByNom() {
        List<Bandas> result = bandasRepository.findByNom("Metallica");
        assertEquals(1, result.size());
        assertTrue(banda1.equals(result.get(0)));
    }

    @Test
    public void testFindByGenero() {
        List<Bandas> result = bandasRepository.findByGenero("Grunge");
        assertEquals(2, result.size());  
        assertTrue(result.contains(banda2)); 
    }
    
    @Test
    public void testFindByPais() {
        List<Bandas> result = bandasRepository.findByPais("USA");
        assertEquals(2, result.size());
        assertThat(result).containsExactlyInAnyOrder(banda1, banda2);
    }

    @Test
    public void testFindByActivos() {
        List<Bandas> result = bandasRepository.findByActivos(true);
        assertEquals(2, result.size());
        assertThat(result).containsExactlyInAnyOrder(banda1, banda3);
    }

    // **************************************************************
    //
    // Private Methods
    //
    // **************************************************************

    private void initObjects() {
        banda1 = new Bandas();
        banda1.setNom("Metallica");
        banda1.setGeneroPrincipal("Rock");
        banda1.setPaisOrigen("USA");
        banda1.setIsActive(true);

        banda2 = new Bandas();
        banda2.setNom("Nirvana");
        banda2.setGeneroPrincipal("Grunge");
        banda2.setPaisOrigen("USA");
        banda2.setIsActive(false);

        banda3 = new Bandas();
        banda3.setNom("The Beatles");
        banda3.setGeneroPrincipal("Rock");
        banda3.setPaisOrigen("UK");
        banda3.setIsActive(true);

        banda4 = new Bandas();
        banda4.setNom("Queen");
        banda4.setGeneroPrincipal("Rock");
        banda4.setPaisOrigen("UK");
        banda4.setIsActive(false);

        bandasRepository.save(banda1);
        bandasRepository.save(banda2);
        bandasRepository.save(banda3);
        bandasRepository.save(banda4);
    }
}

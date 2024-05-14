package com.fernandoHidalgo.gestionBandas.backend.integration.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fernandoHidalgo.gestionBandas.backend.business.model.Bandas;

public interface BandasRepository extends JpaRepository<Bandas, Long> {

    List<Bandas> findByNom(String nom);
    
    List<Bandas> findByGenero(String genero);

    List<Bandas> findByPais(String pais);

    List<Bandas> findByActivos(boolean activos);
    
    // Aquí puedes agregar otros métodos personalizados de consulta si los necesitas
}

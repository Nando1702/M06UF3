package com.fernandoHidalgo.gestionBandas.backend.presentation.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoHidalgo.gestionBandas.backend.business.model.Bandas;
import com.fernandoHidalgo.gestionBandas.backend.business.services.BandaServices;
import com.fernandoHidalgo.gestionBandas.backend.presentation.config.RespuestaError;

@RestController
@RequestMapping("/bandas")
public class BandaController {

    @Autowired
    private BandaServices bandaServices;

    @GetMapping
    public List<Bandas> getAll() {
        return bandaServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Bandas> optional = bandaServices.read(id);

        if (!optional.isPresent()) {
            RespuestaError respuestaError = new RespuestaError("No se encuentra la banda con id " + id);
            return new ResponseEntity<>(respuestaError, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Bandas banda) {
        Long codigo = null;

        try {
            codigo = bandaServices.create(banda);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e.getMessage());
        }

        URI uri = URI.create("/bandas/" + codigo);

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        try {
            bandaServices.delete(id);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No se encuentra la banda con id [" + id + "]. No se ha podido eliminar.");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody Bandas banda) {
        banda.setId(id); // Asegurarse de establecer el ID de la banda
        try {
            bandaServices.update(banda);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Gestión de excepciones

    @ExceptionHandler({IllegalArgumentException.class, ClassCastException.class})
    public ResponseEntity<?> gestor1(Exception e) {
        return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> gestor2(Exception e) {
        return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
    }
}

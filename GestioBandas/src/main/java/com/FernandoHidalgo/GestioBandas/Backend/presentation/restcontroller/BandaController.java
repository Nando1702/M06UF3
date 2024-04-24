package com.FernandoHidalgo.GestioBandas.Backend.presentation.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FernandoHidalgo.GestioBandas.Backend.business.model.Bandas;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        if (id > 500) {
            throw new RuntimeException("El número " + id + " no es válido.");
        }

        Optional<Bandas> optional = bandaServices.read(id);

        if (!optional.isPresent()) {
            RespuestaError respuestaError = new RespuestaError("No se encuentra la banda con id " + id);
            return new ResponseEntity<>(respuestaError, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/crear")
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

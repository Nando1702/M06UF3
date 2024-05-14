package com.fernandoHidalgo.gestionBandas.backend.business.model.dtos;

import java.io.Serializable;

public class Bandas1DTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String generoPrincipal;

    public Bandas1DTO(String name, String generoPrincipal) {
        this.name = name;
        this.generoPrincipal = generoPrincipal;
    }

    public String getName() {
        return name;
    }

    public String getGeneroPrincipal() {
        return generoPrincipal;
    }

    @Override
    public String toString() {
        return "Bandas1DTO [name=" + name + ", generoPrincipal=" + generoPrincipal + "]";
    }
}

package com.fernandoHidalgo.gestionBandas.backend.business.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BANDAS")
public class Bandas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String genero;
   
   
    private LocalDate fecha;

    private String pais;
    private Boolean activos;

    public Bandas() {
    }

    public Bandas(Long id, String nom, String generoPrincipal, LocalDate fechaFundacion, String paisOrigen, Boolean isActive) {
        this.id = id;
        this.nom = nom;
        this.genero = generoPrincipal;
        this.fecha = fechaFundacion;
        this.pais = paisOrigen;
        this.activos = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGeneroPrincipal() {
        return genero;
    }

    public void setGeneroPrincipal(String generoPrincipal) {
        this.genero = generoPrincipal;
    }

    public LocalDate getFechaFundacion() {
        return fecha;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fecha = fechaFundacion;
    }

    public String getPaisOrigen() {
        return pais;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.pais = paisOrigen;
    }

    public Boolean getIsActive() {
        return activos;
    }

    public void setIsActive(Boolean isActive) {
        this.activos = isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
        if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
        Bandas other = (Bandas) obj;
        return Objects.equals(id, other.id) && Objects.equals(nom, other.nom);
    }
}

package com.FernandoHidalgo.GestioBandas.Backend.business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class Bandas implements Serializable{
	
	private Long id;
	private String nom;
	private String generoPrincipal;
	private Optional<String[]> generosSecundarios;
	private Date fechaFundacion;
	private String paisOrigen;
	private Boolean isActive;
	
	public Bandas(Long id, String nom, String generoPrincipal, Optional<String[]> generosSecundarios,
			Date fechaFundacion, String paisOrigen, Boolean isActive) {
		super();
		this.id = id;
		this.nom = nom;
		this.generoPrincipal = generoPrincipal;
		this.generosSecundarios = generosSecundarios;
		this.fechaFundacion = fechaFundacion;
		this.paisOrigen = paisOrigen;
		this.isActive = isActive;
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
		return generoPrincipal;
	}

	public void setGeneroPrincipal(String generoPrincipal) {
		this.generoPrincipal = generoPrincipal;
	}

	public Optional<String[]> getGenerosSecundarios() {
		return generosSecundarios;
	}

	public void setGenerosSecundarios(Optional<String[]> generosSecundarios) {
		this.generosSecundarios = generosSecundarios;
	}

	public Date getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(Date fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bandas other = (Bandas) obj;
		return Objects.equals(id, other.id) && Objects.equals(nom, other.nom);
	}
	
	
}

package com.FernandoHidalgo.GestioBandas.Backend.presentation.restcontroller;

import java.io.Serializable;

public class RespuestaError implements Serializable {
	private static final long serialVersionUID = 1L;

	private String error;
	
	public RespuestaError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
	
}
package io.danny.back_end.model;

import io.danny.back_end.pojos.Beneficios;



public class ThFormatoDTO {
	
	private Beneficios beneficios;

	
	public ThFormatoDTO() {
		
	}

	
	public ThFormatoDTO(Beneficios beneficios) {
		
		this.beneficios = beneficios;
	}

	public Beneficios getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(Beneficios beneficios) {
		this.beneficios = beneficios;
	}
	
	
	
	
	
}

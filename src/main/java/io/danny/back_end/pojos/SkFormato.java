package io.danny.back_end.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SkFormato {
	
	 @NotNull
	    @Size(max = 50)
	 public String beneficio;

	public String getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	 
}

package io.danny.back_end.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class BeneficiosDTO {

    private Long idBen;

    @NotNull
    @Size(max = 50)
    private String nombreBen;

    @NotNull
    @Size(max = 5)
    private String tipoBen;
    
    
    private Long estadoBen;

    public Long getIdBen() {
        return idBen;
    }

    public Long getEstadoBen() {
		return estadoBen;
	}

	public void setEstadoBen(Long estadoBen) {
		this.estadoBen = estadoBen;
	}

	public void setIdBen(final Long idBen) {
        this.idBen = idBen;
    }

    public String getNombreBen() {
        return nombreBen;
    }

    public void setNombreBen(final String nombreBen) {
        this.nombreBen = nombreBen;
    }

    public String getTipoBen() {
        return tipoBen;
    }

    public void setTipoBen(final String tipoBen) {
        this.tipoBen = tipoBen;
    }

}

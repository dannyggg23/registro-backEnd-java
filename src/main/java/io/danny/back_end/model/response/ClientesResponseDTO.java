package io.danny.back_end.model.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientesResponseDTO {

	  private Long idCli;

	    @NotNull
	    @Size(max = 100)
	    private String nombreCli;

	    @NotNull
	    @Size(max = 50)
	    private String correoCli;

	    @Size(max = 13)
	    private String numeroCli;

	    @NotNull
	    @Size(max = 5)
	    private String grupoCli;

	   
	    private String  nombreBen;

	    public Long getIdCli() {
	        return idCli;
	    }

	    public void setIdCli(final Long idCli) {
	        this.idCli = idCli;
	    }

	    public String getNombreCli() {
	        return nombreCli;
	    }

	    public void setNombreCli(final String nombreCli) {
	        this.nombreCli = nombreCli;
	    }

	    public String getCorreoCli() {
	        return correoCli;
	    }

	    public void setCorreoCli(final String correoCli) {
	        this.correoCli = correoCli;
	    }

	    public String getNumeroCli() {
	        return numeroCli;
	    }

	    public void setNumeroCli(final String numeroCli) {
	        this.numeroCli = numeroCli;
	    }

	    public String getGrupoCli() {
	        return grupoCli;
	    }

	    public void setGrupoCli(final String grupoCli) {
	        this.grupoCli = grupoCli;
	    }

		public String getNombreBen() {
			return nombreBen;
		}

		public void setNombreBen(String nombreBen) {
			this.nombreBen = nombreBen;
		}

	  
}

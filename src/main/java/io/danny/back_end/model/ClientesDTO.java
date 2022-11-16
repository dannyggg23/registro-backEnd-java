package io.danny.back_end.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ClientesDTO {

    private Long idCli;

    @NotNull
    @Size(max = 100)
    @Size(min = 3)
    private String nombreCli;

    @NotNull
    @Size(max = 50)
    private String correoCli;

    @Size(max = 13)
    private String numeroCli;

    @NotNull
    @Size(max = 5)
    private String grupoCli;

   
    private Long idBen;

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

    public Long getIdBen() {
        return idBen;
    }

    public void setIdBen(final Long idBen) {
        this.idBen = idBen;
    }

}

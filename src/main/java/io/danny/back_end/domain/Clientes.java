package io.danny.back_end.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Clientes {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long idCli;

    @Column(nullable = false, length = 100)
    private String nombreCli;

    @Column(nullable = false, length = 50)
    private String correoCli;

    @Column(length = 13)
    private String numeroCli;

    @Column(nullable = false, length = 5)
    private String grupoCli;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ben_id", nullable = false)
    private Beneficios idBen;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Beneficios getIdBen() {
        return idBen;
    }

    public void setIdBen(final Beneficios idBen) {
        this.idBen = idBen;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}

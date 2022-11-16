package io.danny.back_end.domain;

import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Beneficios {

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
    private Long idBen;

    @Column(nullable = false, length = 50)
    private String nombreBen;

    @Column(nullable = false, length = 5)
    private String tipoBen;
    
    @Column(nullable = false)
    private Long estadoBen;
    

    public Long getEstadoBen() {
		return estadoBen;
	}

	public void setEstadoBen(Long estadoBen) {
		this.estadoBen = estadoBen;
	}

	@OneToMany(mappedBy = "idBen")
    private Set<Clientes> idBenClientess;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getIdBen() {
        return idBen;
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

    public Set<Clientes> getIdBenClientess() {
        return idBenClientess;
    }

    public void setIdBenClientess(final Set<Clientes> idBenClientess) {
        this.idBenClientess = idBenClientess;
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

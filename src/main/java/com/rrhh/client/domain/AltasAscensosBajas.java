package com.rrhh.client.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AltasAscensosBajas.
 */
@Entity
@Table(name = "altas_ascensos_bajas")
public class AltasAscensosBajas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "expediente")
    private String expediente;

    @Column(name = "prestaservicioen")
    private String prestaservicioen;

    @ManyToOne
    @JsonIgnoreProperties("altasAscensosBajas")
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public AltasAscensosBajas fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCargo() {
        return cargo;
    }

    public AltasAscensosBajas cargo(String cargo) {
        this.cargo = cargo;
        return this;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public AltasAscensosBajas observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getExpediente() {
        return expediente;
    }

    public AltasAscensosBajas expediente(String expediente) {
        this.expediente = expediente;
        return this;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getPrestaservicioen() {
        return prestaservicioen;
    }

    public AltasAscensosBajas prestaservicioen(String prestaservicioen) {
        this.prestaservicioen = prestaservicioen;
        return this;
    }

    public void setPrestaservicioen(String prestaservicioen) {
        this.prestaservicioen = prestaservicioen;
    }

    public Persona getPersona() {
        return persona;
    }

    public AltasAscensosBajas persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AltasAscensosBajas)) {
            return false;
        }
        return id != null && id.equals(((AltasAscensosBajas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AltasAscensosBajas{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", cargo='" + getCargo() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", expediente='" + getExpediente() + "'" +
            ", prestaservicioen='" + getPrestaservicioen() + "'" +
            "}";
    }
}

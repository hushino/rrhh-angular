package com.rrhh.client.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Garantia.
 */
@Entity
@Table(name = "garantia")
public class Garantia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "presentada_fecha")
    private LocalDate presentadaFecha;

    @Column(name = "garantia")
    private String garantia;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne
    @JsonIgnoreProperties("garantias")
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPresentadaFecha() {
        return presentadaFecha;
    }

    public Garantia presentadaFecha(LocalDate presentadaFecha) {
        this.presentadaFecha = presentadaFecha;
        return this;
    }

    public void setPresentadaFecha(LocalDate presentadaFecha) {
        this.presentadaFecha = presentadaFecha;
    }

    public String getGarantia() {
        return garantia;
    }

    public Garantia garantia(String garantia) {
        this.garantia = garantia;
        return this;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Garantia observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Persona getPersona() {
        return persona;
    }

    public Garantia persona(Persona persona) {
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
        if (!(o instanceof Garantia)) {
            return false;
        }
        return id != null && id.equals(((Garantia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Garantia{" +
            "id=" + getId() +
            ", presentadaFecha='" + getPresentadaFecha() + "'" +
            ", garantia='" + getGarantia() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            "}";
    }
}

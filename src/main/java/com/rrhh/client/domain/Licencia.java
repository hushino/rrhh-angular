package com.rrhh.client.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Licencia.
 */
@Entity
@Table(name = "licencia")
public class Licencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_licencia")
    private LocalDate fechaLicencia;

    @Column(name = "referencias")
    private String referencias;

    @Column(name = "numero_de_dias")
    private String numeroDeDias;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "usuarios_mod")
    private String usuariosMod;

    @ManyToOne
    @JsonIgnoreProperties("licencias")
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaLicencia() {
        return fechaLicencia;
    }

    public Licencia fechaLicencia(LocalDate fechaLicencia) {
        this.fechaLicencia = fechaLicencia;
        return this;
    }

    public void setFechaLicencia(LocalDate fechaLicencia) {
        this.fechaLicencia = fechaLicencia;
    }

    public String getReferencias() {
        return referencias;
    }

    public Licencia referencias(String referencias) {
        this.referencias = referencias;
        return this;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getNumeroDeDias() {
        return numeroDeDias;
    }

    public Licencia numeroDeDias(String numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
        return this;
    }

    public void setNumeroDeDias(String numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Licencia observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUsuariosMod() {
        return usuariosMod;
    }

    public Licencia usuariosMod(String usuariosMod) {
        this.usuariosMod = usuariosMod;
        return this;
    }

    public void setUsuariosMod(String usuariosMod) {
        this.usuariosMod = usuariosMod;
    }

    public Persona getPersona() {
        return persona;
    }

    public Licencia persona(Persona persona) {
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
        if (!(o instanceof Licencia)) {
            return false;
        }
        return id != null && id.equals(((Licencia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Licencia{" +
            "id=" + getId() +
            ", fechaLicencia='" + getFechaLicencia() + "'" +
            ", referencias='" + getReferencias() + "'" +
            ", numeroDeDias='" + getNumeroDeDias() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", usuariosMod='" + getUsuariosMod() + "'" +
            "}";
    }
}

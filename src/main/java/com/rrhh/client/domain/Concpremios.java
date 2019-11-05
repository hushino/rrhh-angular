package com.rrhh.client.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Concpremios.
 */
@Entity
@Table(name = "concpremios")
public class Concpremios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "referencias")
    private String referencias;

    @ManyToOne
    @JsonIgnoreProperties("concpremios")
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

    public Concpremios fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getReferencias() {
        return referencias;
    }

    public Concpremios referencias(String referencias) {
        this.referencias = referencias;
        return this;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public Persona getPersona() {
        return persona;
    }

    public Concpremios persona(Persona persona) {
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
        if (!(o instanceof Concpremios)) {
            return false;
        }
        return id != null && id.equals(((Concpremios) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Concpremios{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", referencias='" + getReferencias() + "'" +
            "}";
    }
}

package com.rrhh.client.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Embargos.
 */
@Entity
@Table(name = "embargos")
public class Embargos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "juzgado")
    private String juzgado;

    @Column(name = "acreedor")
    private String acreedor;

    @Column(name = "cantidad")
    private String cantidad;

    @Column(name = "expediente")
    private String expediente;

    @Column(name = "fianza_o_deuda_propia")
    private String fianzaODeudaPropia;

    @Column(name = "origen_de_la_deuda")
    private String origenDeLaDeuda;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "levantada")
    private String levantada;

    @ManyToOne
    @JsonIgnoreProperties("embargos")
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

    public Embargos fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public Embargos juzgado(String juzgado) {
        this.juzgado = juzgado;
        return this;
    }

    public void setJuzgado(String juzgado) {
        this.juzgado = juzgado;
    }

    public String getAcreedor() {
        return acreedor;
    }

    public Embargos acreedor(String acreedor) {
        this.acreedor = acreedor;
        return this;
    }

    public void setAcreedor(String acreedor) {
        this.acreedor = acreedor;
    }

    public String getCantidad() {
        return cantidad;
    }

    public Embargos cantidad(String cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getExpediente() {
        return expediente;
    }

    public Embargos expediente(String expediente) {
        this.expediente = expediente;
        return this;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getFianzaODeudaPropia() {
        return fianzaODeudaPropia;
    }

    public Embargos fianzaODeudaPropia(String fianzaODeudaPropia) {
        this.fianzaODeudaPropia = fianzaODeudaPropia;
        return this;
    }

    public void setFianzaODeudaPropia(String fianzaODeudaPropia) {
        this.fianzaODeudaPropia = fianzaODeudaPropia;
    }

    public String getOrigenDeLaDeuda() {
        return origenDeLaDeuda;
    }

    public Embargos origenDeLaDeuda(String origenDeLaDeuda) {
        this.origenDeLaDeuda = origenDeLaDeuda;
        return this;
    }

    public void setOrigenDeLaDeuda(String origenDeLaDeuda) {
        this.origenDeLaDeuda = origenDeLaDeuda;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Embargos observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getLevantada() {
        return levantada;
    }

    public Embargos levantada(String levantada) {
        this.levantada = levantada;
        return this;
    }

    public void setLevantada(String levantada) {
        this.levantada = levantada;
    }

    public Persona getPersona() {
        return persona;
    }

    public Embargos persona(Persona persona) {
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
        if (!(o instanceof Embargos)) {
            return false;
        }
        return id != null && id.equals(((Embargos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Embargos{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", juzgado='" + getJuzgado() + "'" +
            ", acreedor='" + getAcreedor() + "'" +
            ", cantidad='" + getCantidad() + "'" +
            ", expediente='" + getExpediente() + "'" +
            ", fianzaODeudaPropia='" + getFianzaODeudaPropia() + "'" +
            ", origenDeLaDeuda='" + getOrigenDeLaDeuda() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", levantada='" + getLevantada() + "'" +
            "}";
    }
}

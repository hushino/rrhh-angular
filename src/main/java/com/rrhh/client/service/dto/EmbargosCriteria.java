package com.rrhh.client.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.rrhh.client.domain.Embargos} entity. This class is used
 * in {@link com.rrhh.client.web.rest.EmbargosResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /embargos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmbargosCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fecha;

    private StringFilter juzgado;

    private StringFilter acreedor;

    private StringFilter cantidad;

    private StringFilter expediente;

    private StringFilter fianzaODeudaPropia;

    private StringFilter origenDeLaDeuda;

    private StringFilter observaciones;

    private StringFilter levantada;

    private LongFilter personaId;

    public EmbargosCriteria(){
    }

    public EmbargosCriteria(EmbargosCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.juzgado = other.juzgado == null ? null : other.juzgado.copy();
        this.acreedor = other.acreedor == null ? null : other.acreedor.copy();
        this.cantidad = other.cantidad == null ? null : other.cantidad.copy();
        this.expediente = other.expediente == null ? null : other.expediente.copy();
        this.fianzaODeudaPropia = other.fianzaODeudaPropia == null ? null : other.fianzaODeudaPropia.copy();
        this.origenDeLaDeuda = other.origenDeLaDeuda == null ? null : other.origenDeLaDeuda.copy();
        this.observaciones = other.observaciones == null ? null : other.observaciones.copy();
        this.levantada = other.levantada == null ? null : other.levantada.copy();
        this.personaId = other.personaId == null ? null : other.personaId.copy();
    }

    @Override
    public EmbargosCriteria copy() {
        return new EmbargosCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateFilter fecha) {
        this.fecha = fecha;
    }

    public StringFilter getJuzgado() {
        return juzgado;
    }

    public void setJuzgado(StringFilter juzgado) {
        this.juzgado = juzgado;
    }

    public StringFilter getAcreedor() {
        return acreedor;
    }

    public void setAcreedor(StringFilter acreedor) {
        this.acreedor = acreedor;
    }

    public StringFilter getCantidad() {
        return cantidad;
    }

    public void setCantidad(StringFilter cantidad) {
        this.cantidad = cantidad;
    }

    public StringFilter getExpediente() {
        return expediente;
    }

    public void setExpediente(StringFilter expediente) {
        this.expediente = expediente;
    }

    public StringFilter getFianzaODeudaPropia() {
        return fianzaODeudaPropia;
    }

    public void setFianzaODeudaPropia(StringFilter fianzaODeudaPropia) {
        this.fianzaODeudaPropia = fianzaODeudaPropia;
    }

    public StringFilter getOrigenDeLaDeuda() {
        return origenDeLaDeuda;
    }

    public void setOrigenDeLaDeuda(StringFilter origenDeLaDeuda) {
        this.origenDeLaDeuda = origenDeLaDeuda;
    }

    public StringFilter getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(StringFilter observaciones) {
        this.observaciones = observaciones;
    }

    public StringFilter getLevantada() {
        return levantada;
    }

    public void setLevantada(StringFilter levantada) {
        this.levantada = levantada;
    }

    public LongFilter getPersonaId() {
        return personaId;
    }

    public void setPersonaId(LongFilter personaId) {
        this.personaId = personaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmbargosCriteria that = (EmbargosCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(juzgado, that.juzgado) &&
            Objects.equals(acreedor, that.acreedor) &&
            Objects.equals(cantidad, that.cantidad) &&
            Objects.equals(expediente, that.expediente) &&
            Objects.equals(fianzaODeudaPropia, that.fianzaODeudaPropia) &&
            Objects.equals(origenDeLaDeuda, that.origenDeLaDeuda) &&
            Objects.equals(observaciones, that.observaciones) &&
            Objects.equals(levantada, that.levantada) &&
            Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fecha,
        juzgado,
        acreedor,
        cantidad,
        expediente,
        fianzaODeudaPropia,
        origenDeLaDeuda,
        observaciones,
        levantada,
        personaId
        );
    }

    @Override
    public String toString() {
        return "EmbargosCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (juzgado != null ? "juzgado=" + juzgado + ", " : "") +
                (acreedor != null ? "acreedor=" + acreedor + ", " : "") +
                (cantidad != null ? "cantidad=" + cantidad + ", " : "") +
                (expediente != null ? "expediente=" + expediente + ", " : "") +
                (fianzaODeudaPropia != null ? "fianzaODeudaPropia=" + fianzaODeudaPropia + ", " : "") +
                (origenDeLaDeuda != null ? "origenDeLaDeuda=" + origenDeLaDeuda + ", " : "") +
                (observaciones != null ? "observaciones=" + observaciones + ", " : "") +
                (levantada != null ? "levantada=" + levantada + ", " : "") +
                (personaId != null ? "personaId=" + personaId + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the {@link com.rrhh.client.domain.AltasAscensosBajas} entity. This class is used
 * in {@link com.rrhh.client.web.rest.AltasAscensosBajasResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /altas-ascensos-bajas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AltasAscensosBajasCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fecha;

    private StringFilter cargo;

    private StringFilter observaciones;

    private StringFilter expediente;

    private StringFilter prestaservicioen;

    private LongFilter personaId;

    public AltasAscensosBajasCriteria(){
    }

    public AltasAscensosBajasCriteria(AltasAscensosBajasCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.cargo = other.cargo == null ? null : other.cargo.copy();
        this.observaciones = other.observaciones == null ? null : other.observaciones.copy();
        this.expediente = other.expediente == null ? null : other.expediente.copy();
        this.prestaservicioen = other.prestaservicioen == null ? null : other.prestaservicioen.copy();
        this.personaId = other.personaId == null ? null : other.personaId.copy();
    }

    @Override
    public AltasAscensosBajasCriteria copy() {
        return new AltasAscensosBajasCriteria(this);
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

    public StringFilter getCargo() {
        return cargo;
    }

    public void setCargo(StringFilter cargo) {
        this.cargo = cargo;
    }

    public StringFilter getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(StringFilter observaciones) {
        this.observaciones = observaciones;
    }

    public StringFilter getExpediente() {
        return expediente;
    }

    public void setExpediente(StringFilter expediente) {
        this.expediente = expediente;
    }

    public StringFilter getPrestaservicioen() {
        return prestaservicioen;
    }

    public void setPrestaservicioen(StringFilter prestaservicioen) {
        this.prestaservicioen = prestaservicioen;
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
        final AltasAscensosBajasCriteria that = (AltasAscensosBajasCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(cargo, that.cargo) &&
            Objects.equals(observaciones, that.observaciones) &&
            Objects.equals(expediente, that.expediente) &&
            Objects.equals(prestaservicioen, that.prestaservicioen) &&
            Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fecha,
        cargo,
        observaciones,
        expediente,
        prestaservicioen,
        personaId
        );
    }

    @Override
    public String toString() {
        return "AltasAscensosBajasCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (cargo != null ? "cargo=" + cargo + ", " : "") +
                (observaciones != null ? "observaciones=" + observaciones + ", " : "") +
                (expediente != null ? "expediente=" + expediente + ", " : "") +
                (prestaservicioen != null ? "prestaservicioen=" + prestaservicioen + ", " : "") +
                (personaId != null ? "personaId=" + personaId + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the {@link com.rrhh.client.domain.Garantia} entity. This class is used
 * in {@link com.rrhh.client.web.rest.GarantiaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /garantias?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GarantiaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter presentadaFecha;

    private StringFilter garantia;

    private StringFilter observaciones;

    private LongFilter personaId;

    public GarantiaCriteria(){
    }

    public GarantiaCriteria(GarantiaCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.presentadaFecha = other.presentadaFecha == null ? null : other.presentadaFecha.copy();
        this.garantia = other.garantia == null ? null : other.garantia.copy();
        this.observaciones = other.observaciones == null ? null : other.observaciones.copy();
        this.personaId = other.personaId == null ? null : other.personaId.copy();
    }

    @Override
    public GarantiaCriteria copy() {
        return new GarantiaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getPresentadaFecha() {
        return presentadaFecha;
    }

    public void setPresentadaFecha(LocalDateFilter presentadaFecha) {
        this.presentadaFecha = presentadaFecha;
    }

    public StringFilter getGarantia() {
        return garantia;
    }

    public void setGarantia(StringFilter garantia) {
        this.garantia = garantia;
    }

    public StringFilter getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(StringFilter observaciones) {
        this.observaciones = observaciones;
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
        final GarantiaCriteria that = (GarantiaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(presentadaFecha, that.presentadaFecha) &&
            Objects.equals(garantia, that.garantia) &&
            Objects.equals(observaciones, that.observaciones) &&
            Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        presentadaFecha,
        garantia,
        observaciones,
        personaId
        );
    }

    @Override
    public String toString() {
        return "GarantiaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (presentadaFecha != null ? "presentadaFecha=" + presentadaFecha + ", " : "") +
                (garantia != null ? "garantia=" + garantia + ", " : "") +
                (observaciones != null ? "observaciones=" + observaciones + ", " : "") +
                (personaId != null ? "personaId=" + personaId + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the {@link com.rrhh.client.domain.PenasDisciplinariasSufridas} entity. This class is used
 * in {@link com.rrhh.client.web.rest.PenasDisciplinariasSufridasResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /penas-disciplinarias-sufridas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PenasDisciplinariasSufridasCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fecha;

    private StringFilter expediente;

    private StringFilter referencias;

    private LongFilter personaId;

    public PenasDisciplinariasSufridasCriteria(){
    }

    public PenasDisciplinariasSufridasCriteria(PenasDisciplinariasSufridasCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.expediente = other.expediente == null ? null : other.expediente.copy();
        this.referencias = other.referencias == null ? null : other.referencias.copy();
        this.personaId = other.personaId == null ? null : other.personaId.copy();
    }

    @Override
    public PenasDisciplinariasSufridasCriteria copy() {
        return new PenasDisciplinariasSufridasCriteria(this);
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

    public StringFilter getExpediente() {
        return expediente;
    }

    public void setExpediente(StringFilter expediente) {
        this.expediente = expediente;
    }

    public StringFilter getReferencias() {
        return referencias;
    }

    public void setReferencias(StringFilter referencias) {
        this.referencias = referencias;
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
        final PenasDisciplinariasSufridasCriteria that = (PenasDisciplinariasSufridasCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(expediente, that.expediente) &&
            Objects.equals(referencias, that.referencias) &&
            Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fecha,
        expediente,
        referencias,
        personaId
        );
    }

    @Override
    public String toString() {
        return "PenasDisciplinariasSufridasCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (expediente != null ? "expediente=" + expediente + ", " : "") +
                (referencias != null ? "referencias=" + referencias + ", " : "") +
                (personaId != null ? "personaId=" + personaId + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the {@link com.rrhh.client.domain.Licencia} entity. This class is used
 * in {@link com.rrhh.client.web.rest.LicenciaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /licencias?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LicenciaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fechaLicencia;

    private StringFilter referencias;

    private StringFilter numeroDeDias;

    private StringFilter observaciones;

    private StringFilter usuariosMod;

    private LongFilter personaId;

    public LicenciaCriteria(){
    }

    public LicenciaCriteria(LicenciaCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fechaLicencia = other.fechaLicencia == null ? null : other.fechaLicencia.copy();
        this.referencias = other.referencias == null ? null : other.referencias.copy();
        this.numeroDeDias = other.numeroDeDias == null ? null : other.numeroDeDias.copy();
        this.observaciones = other.observaciones == null ? null : other.observaciones.copy();
        this.usuariosMod = other.usuariosMod == null ? null : other.usuariosMod.copy();
        this.personaId = other.personaId == null ? null : other.personaId.copy();
    }

    @Override
    public LicenciaCriteria copy() {
        return new LicenciaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFechaLicencia() {
        return fechaLicencia;
    }

    public void setFechaLicencia(LocalDateFilter fechaLicencia) {
        this.fechaLicencia = fechaLicencia;
    }

    public StringFilter getReferencias() {
        return referencias;
    }

    public void setReferencias(StringFilter referencias) {
        this.referencias = referencias;
    }

    public StringFilter getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(StringFilter numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public StringFilter getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(StringFilter observaciones) {
        this.observaciones = observaciones;
    }

    public StringFilter getUsuariosMod() {
        return usuariosMod;
    }

    public void setUsuariosMod(StringFilter usuariosMod) {
        this.usuariosMod = usuariosMod;
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
        final LicenciaCriteria that = (LicenciaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fechaLicencia, that.fechaLicencia) &&
            Objects.equals(referencias, that.referencias) &&
            Objects.equals(numeroDeDias, that.numeroDeDias) &&
            Objects.equals(observaciones, that.observaciones) &&
            Objects.equals(usuariosMod, that.usuariosMod) &&
            Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fechaLicencia,
        referencias,
        numeroDeDias,
        observaciones,
        usuariosMod,
        personaId
        );
    }

    @Override
    public String toString() {
        return "LicenciaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fechaLicencia != null ? "fechaLicencia=" + fechaLicencia + ", " : "") +
                (referencias != null ? "referencias=" + referencias + ", " : "") +
                (numeroDeDias != null ? "numeroDeDias=" + numeroDeDias + ", " : "") +
                (observaciones != null ? "observaciones=" + observaciones + ", " : "") +
                (usuariosMod != null ? "usuariosMod=" + usuariosMod + ", " : "") +
                (personaId != null ? "personaId=" + personaId + ", " : "") +
            "}";
    }

}

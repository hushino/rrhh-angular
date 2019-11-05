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

/**
 * Criteria class for the {@link com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios} entity. This class is used
 * in {@link com.rrhh.client.web.rest.ConceptoConocimientosEspecialesClasificacionPremiosResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /concepto-conocimientos-especiales-clasificacion-premios?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ConceptoConocimientosEspecialesClasificacionPremiosCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    public ConceptoConocimientosEspecialesClasificacionPremiosCriteria(){
    }

    public ConceptoConocimientosEspecialesClasificacionPremiosCriteria(ConceptoConocimientosEspecialesClasificacionPremiosCriteria other){
        this.id = other.id == null ? null : other.id.copy();
    }

    @Override
    public ConceptoConocimientosEspecialesClasificacionPremiosCriteria copy() {
        return new ConceptoConocimientosEspecialesClasificacionPremiosCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ConceptoConocimientosEspecialesClasificacionPremiosCriteria that = (ConceptoConocimientosEspecialesClasificacionPremiosCriteria) o;
        return
            Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id
        );
    }

    @Override
    public String toString() {
        return "ConceptoConocimientosEspecialesClasificacionPremiosCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
            "}";
    }

}

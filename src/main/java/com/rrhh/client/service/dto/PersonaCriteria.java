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
 * Criteria class for the {@link com.rrhh.client.domain.Persona} entity. This class is used
 * in {@link com.rrhh.client.web.rest.PersonaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /personas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PersonaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private StringFilter apellido;

    private StringFilter cuil;

    private IntegerFilter dni;

    private IntegerFilter legajo;

    private StringFilter apodo;

    private StringFilter soltero;

    private StringFilter casado;

    private StringFilter conviviente;

    private StringFilter viudo;

    private StringFilter domicilio;

    private StringFilter lugar;

    private StringFilter calle;

    private StringFilter numero;

    private StringFilter telefonofijo;

    private StringFilter numerodecelular;

    private StringFilter oficioprofecion;

    private StringFilter niveldeestudios;

    private StringFilter gruposanguineo;

    private StringFilter factor;

    private StringFilter donante;

    private StringFilter diabetes;

    private StringFilter hipertension;

    private StringFilter alergias;

    private StringFilter asma;

    private StringFilter otros;

    private LocalDateFilter fechadeingreso;

    private StringFilter instrumentolegal;

    private StringFilter categoria;

    private StringFilter item;

    private StringFilter planta;

    private StringFilter area;

    private StringFilter direccion;

    private IntegerFilter annos;

    private IntegerFilter meses;

    private IntegerFilter dias;

    private StringFilter realizocomputodeservicios;

    private StringFilter poseeconocimientoenmaquinasviales;

    private StringFilter casoemergenciacelular;

    private StringFilter casoemergenciafijo;

    private StringFilter casoemergencianombre;

    private StringFilter casoemergenciacelular2;

    private StringFilter casoemergenciafijo2;

    private StringFilter casoemergencianombre2;

    private StringFilter familiaracargonombre;

    private StringFilter familiaracargonombre2;

    private StringFilter familiaracargonombre3;

    private StringFilter familiaracargonombre4;

    private StringFilter familiaracargonombre5;

    private StringFilter familiaracargodni;

    private StringFilter familiaracargodni2;

    private StringFilter familiaracargodni3;

    private StringFilter familiaracargodni4;

    private StringFilter familiaracargodni5;

    private StringFilter familiaracargoedad;

    private StringFilter familiaracargoedad2;

    private StringFilter familiaracargoedad3;

    private StringFilter familiaracargoedad4;

    private StringFilter familiaracargoedad5;

    private StringFilter altura;

    private StringFilter barrio;

    private StringFilter estudiosincompletos;

    private StringFilter conyugeapellido;

    private StringFilter conyugenombre;

    private IntegerFilter conyugedni;

    private StringFilter conyugecuil;

    private StringFilter grupofamiliarapellidonombre;

    private StringFilter grupofamiliarapellidonombre2;

    private StringFilter grupofamiliarapellidonombre3;

    private StringFilter grupofamiliarapellidonombre4;

    private StringFilter grupofamiliarapellidonombre5;

    private StringFilter grupofamiliarapellidonombre6;

    private StringFilter grupofamiliarapellidonombre7;

    private StringFilter grupofamiliarapellidonombre8;

    private StringFilter grupofamiliarapellidonombre9;

    private StringFilter grupofamiliarapellidonombre10;

    private StringFilter grupofamiliarapellidonombre11;

    private StringFilter grupofamiliarapellidonombreedad;

    private StringFilter grupofamiliarapellidonombreedad2;

    private StringFilter grupofamiliarapellidonombreedad3;

    private StringFilter grupofamiliarapellidonombreedad4;

    private StringFilter grupofamiliarapellidonombreedad5;

    private StringFilter grupofamiliarapellidonombreedad6;

    private StringFilter grupofamiliarapellidonombreedad7;

    private StringFilter grupofamiliarapellidonombreedad8;

    private StringFilter grupofamiliarapellidonombreedad9;

    private StringFilter grupofamiliarapellidonombreedad10;

    private StringFilter grupofamiliarapellidonombreedad11;

    private IntegerFilter grupofamiliarapellidonombredni;

    private IntegerFilter grupofamiliarapellidonombredni2;

    private IntegerFilter grupofamiliarapellidonombredni3;

    private IntegerFilter grupofamiliarapellidonombredni4;

    private IntegerFilter grupofamiliarapellidonombredni5;

    private IntegerFilter grupofamiliarapellidonombredni6;

    private IntegerFilter grupofamiliarapellidonombredni7;

    private IntegerFilter grupofamiliarapellidonombredni8;

    private IntegerFilter grupofamiliarapellidonombredni9;

    private IntegerFilter grupofamiliarapellidonombredni10;

    private IntegerFilter grupofamiliarapellidonombredni11;

    private StringFilter grupofamiliarapellidonombrefamiliar;

    private StringFilter grupofamiliarapellidonombrefamiliar2;

    private StringFilter grupofamiliarapellidonombrefamiliar4;

    private StringFilter grupofamiliarapellidonombrefamiliar3;

    private StringFilter grupofamiliarapellidonombrefamiliar5;

    private StringFilter grupofamiliarapellidonombrefamiliar6;

    private StringFilter grupofamiliarapellidonombrefamiliar7;

    private StringFilter grupofamiliarapellidonombrefamiliar8;

    private StringFilter grupofamiliarapellidonombrefamiliar9;

    private StringFilter grupofamiliarapellidonombrefamiliar10;

    private StringFilter grupofamiliarapellidonombrefamiliar11;

    private LongFilter licenciaId;

    private LongFilter altasAscensosBajasId;

    private LongFilter concpremiosId;

    private LongFilter embargosId;

    private LongFilter garantiaId;

    private LongFilter otrosServiciosPrestadosId;

    private LongFilter penasDisciplinariasSufridasId;

    public PersonaCriteria(){
    }

    public PersonaCriteria(PersonaCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.apellido = other.apellido == null ? null : other.apellido.copy();
        this.cuil = other.cuil == null ? null : other.cuil.copy();
        this.dni = other.dni == null ? null : other.dni.copy();
        this.legajo = other.legajo == null ? null : other.legajo.copy();
        this.apodo = other.apodo == null ? null : other.apodo.copy();
        this.soltero = other.soltero == null ? null : other.soltero.copy();
        this.casado = other.casado == null ? null : other.casado.copy();
        this.conviviente = other.conviviente == null ? null : other.conviviente.copy();
        this.viudo = other.viudo == null ? null : other.viudo.copy();
        this.domicilio = other.domicilio == null ? null : other.domicilio.copy();
        this.lugar = other.lugar == null ? null : other.lugar.copy();
        this.calle = other.calle == null ? null : other.calle.copy();
        this.numero = other.numero == null ? null : other.numero.copy();
        this.telefonofijo = other.telefonofijo == null ? null : other.telefonofijo.copy();
        this.numerodecelular = other.numerodecelular == null ? null : other.numerodecelular.copy();
        this.oficioprofecion = other.oficioprofecion == null ? null : other.oficioprofecion.copy();
        this.niveldeestudios = other.niveldeestudios == null ? null : other.niveldeestudios.copy();
        this.gruposanguineo = other.gruposanguineo == null ? null : other.gruposanguineo.copy();
        this.factor = other.factor == null ? null : other.factor.copy();
        this.donante = other.donante == null ? null : other.donante.copy();
        this.diabetes = other.diabetes == null ? null : other.diabetes.copy();
        this.hipertension = other.hipertension == null ? null : other.hipertension.copy();
        this.alergias = other.alergias == null ? null : other.alergias.copy();
        this.asma = other.asma == null ? null : other.asma.copy();
        this.otros = other.otros == null ? null : other.otros.copy();
        this.fechadeingreso = other.fechadeingreso == null ? null : other.fechadeingreso.copy();
        this.instrumentolegal = other.instrumentolegal == null ? null : other.instrumentolegal.copy();
        this.categoria = other.categoria == null ? null : other.categoria.copy();
        this.item = other.item == null ? null : other.item.copy();
        this.planta = other.planta == null ? null : other.planta.copy();
        this.area = other.area == null ? null : other.area.copy();
        this.direccion = other.direccion == null ? null : other.direccion.copy();
        this.annos = other.annos == null ? null : other.annos.copy();
        this.meses = other.meses == null ? null : other.meses.copy();
        this.dias = other.dias == null ? null : other.dias.copy();
        this.realizocomputodeservicios = other.realizocomputodeservicios == null ? null : other.realizocomputodeservicios.copy();
        this.poseeconocimientoenmaquinasviales = other.poseeconocimientoenmaquinasviales == null ? null : other.poseeconocimientoenmaquinasviales.copy();
        this.casoemergenciacelular = other.casoemergenciacelular == null ? null : other.casoemergenciacelular.copy();
        this.casoemergenciafijo = other.casoemergenciafijo == null ? null : other.casoemergenciafijo.copy();
        this.casoemergencianombre = other.casoemergencianombre == null ? null : other.casoemergencianombre.copy();
        this.casoemergenciacelular2 = other.casoemergenciacelular2 == null ? null : other.casoemergenciacelular2.copy();
        this.casoemergenciafijo2 = other.casoemergenciafijo2 == null ? null : other.casoemergenciafijo2.copy();
        this.casoemergencianombre2 = other.casoemergencianombre2 == null ? null : other.casoemergencianombre2.copy();
        this.familiaracargonombre = other.familiaracargonombre == null ? null : other.familiaracargonombre.copy();
        this.familiaracargonombre2 = other.familiaracargonombre2 == null ? null : other.familiaracargonombre2.copy();
        this.familiaracargonombre3 = other.familiaracargonombre3 == null ? null : other.familiaracargonombre3.copy();
        this.familiaracargonombre4 = other.familiaracargonombre4 == null ? null : other.familiaracargonombre4.copy();
        this.familiaracargonombre5 = other.familiaracargonombre5 == null ? null : other.familiaracargonombre5.copy();
        this.familiaracargodni = other.familiaracargodni == null ? null : other.familiaracargodni.copy();
        this.familiaracargodni2 = other.familiaracargodni2 == null ? null : other.familiaracargodni2.copy();
        this.familiaracargodni3 = other.familiaracargodni3 == null ? null : other.familiaracargodni3.copy();
        this.familiaracargodni4 = other.familiaracargodni4 == null ? null : other.familiaracargodni4.copy();
        this.familiaracargodni5 = other.familiaracargodni5 == null ? null : other.familiaracargodni5.copy();
        this.familiaracargoedad = other.familiaracargoedad == null ? null : other.familiaracargoedad.copy();
        this.familiaracargoedad2 = other.familiaracargoedad2 == null ? null : other.familiaracargoedad2.copy();
        this.familiaracargoedad3 = other.familiaracargoedad3 == null ? null : other.familiaracargoedad3.copy();
        this.familiaracargoedad4 = other.familiaracargoedad4 == null ? null : other.familiaracargoedad4.copy();
        this.familiaracargoedad5 = other.familiaracargoedad5 == null ? null : other.familiaracargoedad5.copy();
        this.altura = other.altura == null ? null : other.altura.copy();
        this.barrio = other.barrio == null ? null : other.barrio.copy();
        this.estudiosincompletos = other.estudiosincompletos == null ? null : other.estudiosincompletos.copy();
        this.conyugeapellido = other.conyugeapellido == null ? null : other.conyugeapellido.copy();
        this.conyugenombre = other.conyugenombre == null ? null : other.conyugenombre.copy();
        this.conyugedni = other.conyugedni == null ? null : other.conyugedni.copy();
        this.conyugecuil = other.conyugecuil == null ? null : other.conyugecuil.copy();
        this.grupofamiliarapellidonombre = other.grupofamiliarapellidonombre == null ? null : other.grupofamiliarapellidonombre.copy();
        this.grupofamiliarapellidonombre2 = other.grupofamiliarapellidonombre2 == null ? null : other.grupofamiliarapellidonombre2.copy();
        this.grupofamiliarapellidonombre3 = other.grupofamiliarapellidonombre3 == null ? null : other.grupofamiliarapellidonombre3.copy();
        this.grupofamiliarapellidonombre4 = other.grupofamiliarapellidonombre4 == null ? null : other.grupofamiliarapellidonombre4.copy();
        this.grupofamiliarapellidonombre5 = other.grupofamiliarapellidonombre5 == null ? null : other.grupofamiliarapellidonombre5.copy();
        this.grupofamiliarapellidonombre6 = other.grupofamiliarapellidonombre6 == null ? null : other.grupofamiliarapellidonombre6.copy();
        this.grupofamiliarapellidonombre7 = other.grupofamiliarapellidonombre7 == null ? null : other.grupofamiliarapellidonombre7.copy();
        this.grupofamiliarapellidonombre8 = other.grupofamiliarapellidonombre8 == null ? null : other.grupofamiliarapellidonombre8.copy();
        this.grupofamiliarapellidonombre9 = other.grupofamiliarapellidonombre9 == null ? null : other.grupofamiliarapellidonombre9.copy();
        this.grupofamiliarapellidonombre10 = other.grupofamiliarapellidonombre10 == null ? null : other.grupofamiliarapellidonombre10.copy();
        this.grupofamiliarapellidonombre11 = other.grupofamiliarapellidonombre11 == null ? null : other.grupofamiliarapellidonombre11.copy();
        this.grupofamiliarapellidonombreedad = other.grupofamiliarapellidonombreedad == null ? null : other.grupofamiliarapellidonombreedad.copy();
        this.grupofamiliarapellidonombreedad2 = other.grupofamiliarapellidonombreedad2 == null ? null : other.grupofamiliarapellidonombreedad2.copy();
        this.grupofamiliarapellidonombreedad3 = other.grupofamiliarapellidonombreedad3 == null ? null : other.grupofamiliarapellidonombreedad3.copy();
        this.grupofamiliarapellidonombreedad4 = other.grupofamiliarapellidonombreedad4 == null ? null : other.grupofamiliarapellidonombreedad4.copy();
        this.grupofamiliarapellidonombreedad5 = other.grupofamiliarapellidonombreedad5 == null ? null : other.grupofamiliarapellidonombreedad5.copy();
        this.grupofamiliarapellidonombreedad6 = other.grupofamiliarapellidonombreedad6 == null ? null : other.grupofamiliarapellidonombreedad6.copy();
        this.grupofamiliarapellidonombreedad7 = other.grupofamiliarapellidonombreedad7 == null ? null : other.grupofamiliarapellidonombreedad7.copy();
        this.grupofamiliarapellidonombreedad8 = other.grupofamiliarapellidonombreedad8 == null ? null : other.grupofamiliarapellidonombreedad8.copy();
        this.grupofamiliarapellidonombreedad9 = other.grupofamiliarapellidonombreedad9 == null ? null : other.grupofamiliarapellidonombreedad9.copy();
        this.grupofamiliarapellidonombreedad10 = other.grupofamiliarapellidonombreedad10 == null ? null : other.grupofamiliarapellidonombreedad10.copy();
        this.grupofamiliarapellidonombreedad11 = other.grupofamiliarapellidonombreedad11 == null ? null : other.grupofamiliarapellidonombreedad11.copy();
        this.grupofamiliarapellidonombredni = other.grupofamiliarapellidonombredni == null ? null : other.grupofamiliarapellidonombredni.copy();
        this.grupofamiliarapellidonombredni2 = other.grupofamiliarapellidonombredni2 == null ? null : other.grupofamiliarapellidonombredni2.copy();
        this.grupofamiliarapellidonombredni3 = other.grupofamiliarapellidonombredni3 == null ? null : other.grupofamiliarapellidonombredni3.copy();
        this.grupofamiliarapellidonombredni4 = other.grupofamiliarapellidonombredni4 == null ? null : other.grupofamiliarapellidonombredni4.copy();
        this.grupofamiliarapellidonombredni5 = other.grupofamiliarapellidonombredni5 == null ? null : other.grupofamiliarapellidonombredni5.copy();
        this.grupofamiliarapellidonombredni6 = other.grupofamiliarapellidonombredni6 == null ? null : other.grupofamiliarapellidonombredni6.copy();
        this.grupofamiliarapellidonombredni7 = other.grupofamiliarapellidonombredni7 == null ? null : other.grupofamiliarapellidonombredni7.copy();
        this.grupofamiliarapellidonombredni8 = other.grupofamiliarapellidonombredni8 == null ? null : other.grupofamiliarapellidonombredni8.copy();
        this.grupofamiliarapellidonombredni9 = other.grupofamiliarapellidonombredni9 == null ? null : other.grupofamiliarapellidonombredni9.copy();
        this.grupofamiliarapellidonombredni10 = other.grupofamiliarapellidonombredni10 == null ? null : other.grupofamiliarapellidonombredni10.copy();
        this.grupofamiliarapellidonombredni11 = other.grupofamiliarapellidonombredni11 == null ? null : other.grupofamiliarapellidonombredni11.copy();
        this.grupofamiliarapellidonombrefamiliar = other.grupofamiliarapellidonombrefamiliar == null ? null : other.grupofamiliarapellidonombrefamiliar.copy();
        this.grupofamiliarapellidonombrefamiliar2 = other.grupofamiliarapellidonombrefamiliar2 == null ? null : other.grupofamiliarapellidonombrefamiliar2.copy();
        this.grupofamiliarapellidonombrefamiliar4 = other.grupofamiliarapellidonombrefamiliar4 == null ? null : other.grupofamiliarapellidonombrefamiliar4.copy();
        this.grupofamiliarapellidonombrefamiliar3 = other.grupofamiliarapellidonombrefamiliar3 == null ? null : other.grupofamiliarapellidonombrefamiliar3.copy();
        this.grupofamiliarapellidonombrefamiliar5 = other.grupofamiliarapellidonombrefamiliar5 == null ? null : other.grupofamiliarapellidonombrefamiliar5.copy();
        this.grupofamiliarapellidonombrefamiliar6 = other.grupofamiliarapellidonombrefamiliar6 == null ? null : other.grupofamiliarapellidonombrefamiliar6.copy();
        this.grupofamiliarapellidonombrefamiliar7 = other.grupofamiliarapellidonombrefamiliar7 == null ? null : other.grupofamiliarapellidonombrefamiliar7.copy();
        this.grupofamiliarapellidonombrefamiliar8 = other.grupofamiliarapellidonombrefamiliar8 == null ? null : other.grupofamiliarapellidonombrefamiliar8.copy();
        this.grupofamiliarapellidonombrefamiliar9 = other.grupofamiliarapellidonombrefamiliar9 == null ? null : other.grupofamiliarapellidonombrefamiliar9.copy();
        this.grupofamiliarapellidonombrefamiliar10 = other.grupofamiliarapellidonombrefamiliar10 == null ? null : other.grupofamiliarapellidonombrefamiliar10.copy();
        this.grupofamiliarapellidonombrefamiliar11 = other.grupofamiliarapellidonombrefamiliar11 == null ? null : other.grupofamiliarapellidonombrefamiliar11.copy();
        this.licenciaId = other.licenciaId == null ? null : other.licenciaId.copy();
        this.altasAscensosBajasId = other.altasAscensosBajasId == null ? null : other.altasAscensosBajasId.copy();
        this.concpremiosId = other.concpremiosId == null ? null : other.concpremiosId.copy();
        this.embargosId = other.embargosId == null ? null : other.embargosId.copy();
        this.garantiaId = other.garantiaId == null ? null : other.garantiaId.copy();
        this.otrosServiciosPrestadosId = other.otrosServiciosPrestadosId == null ? null : other.otrosServiciosPrestadosId.copy();
        this.penasDisciplinariasSufridasId = other.penasDisciplinariasSufridasId == null ? null : other.penasDisciplinariasSufridasId.copy();
    }

    @Override
    public PersonaCriteria copy() {
        return new PersonaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getApellido() {
        return apellido;
    }

    public void setApellido(StringFilter apellido) {
        this.apellido = apellido;
    }

    public StringFilter getCuil() {
        return cuil;
    }

    public void setCuil(StringFilter cuil) {
        this.cuil = cuil;
    }

    public IntegerFilter getDni() {
        return dni;
    }

    public void setDni(IntegerFilter dni) {
        this.dni = dni;
    }

    public IntegerFilter getLegajo() {
        return legajo;
    }

    public void setLegajo(IntegerFilter legajo) {
        this.legajo = legajo;
    }

    public StringFilter getApodo() {
        return apodo;
    }

    public void setApodo(StringFilter apodo) {
        this.apodo = apodo;
    }

    public StringFilter getSoltero() {
        return soltero;
    }

    public void setSoltero(StringFilter soltero) {
        this.soltero = soltero;
    }

    public StringFilter getCasado() {
        return casado;
    }

    public void setCasado(StringFilter casado) {
        this.casado = casado;
    }

    public StringFilter getConviviente() {
        return conviviente;
    }

    public void setConviviente(StringFilter conviviente) {
        this.conviviente = conviviente;
    }

    public StringFilter getViudo() {
        return viudo;
    }

    public void setViudo(StringFilter viudo) {
        this.viudo = viudo;
    }

    public StringFilter getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(StringFilter domicilio) {
        this.domicilio = domicilio;
    }

    public StringFilter getLugar() {
        return lugar;
    }

    public void setLugar(StringFilter lugar) {
        this.lugar = lugar;
    }

    public StringFilter getCalle() {
        return calle;
    }

    public void setCalle(StringFilter calle) {
        this.calle = calle;
    }

    public StringFilter getNumero() {
        return numero;
    }

    public void setNumero(StringFilter numero) {
        this.numero = numero;
    }

    public StringFilter getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(StringFilter telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public StringFilter getNumerodecelular() {
        return numerodecelular;
    }

    public void setNumerodecelular(StringFilter numerodecelular) {
        this.numerodecelular = numerodecelular;
    }

    public StringFilter getOficioprofecion() {
        return oficioprofecion;
    }

    public void setOficioprofecion(StringFilter oficioprofecion) {
        this.oficioprofecion = oficioprofecion;
    }

    public StringFilter getNiveldeestudios() {
        return niveldeestudios;
    }

    public void setNiveldeestudios(StringFilter niveldeestudios) {
        this.niveldeestudios = niveldeestudios;
    }

    public StringFilter getGruposanguineo() {
        return gruposanguineo;
    }

    public void setGruposanguineo(StringFilter gruposanguineo) {
        this.gruposanguineo = gruposanguineo;
    }

    public StringFilter getFactor() {
        return factor;
    }

    public void setFactor(StringFilter factor) {
        this.factor = factor;
    }

    public StringFilter getDonante() {
        return donante;
    }

    public void setDonante(StringFilter donante) {
        this.donante = donante;
    }

    public StringFilter getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(StringFilter diabetes) {
        this.diabetes = diabetes;
    }

    public StringFilter getHipertension() {
        return hipertension;
    }

    public void setHipertension(StringFilter hipertension) {
        this.hipertension = hipertension;
    }

    public StringFilter getAlergias() {
        return alergias;
    }

    public void setAlergias(StringFilter alergias) {
        this.alergias = alergias;
    }

    public StringFilter getAsma() {
        return asma;
    }

    public void setAsma(StringFilter asma) {
        this.asma = asma;
    }

    public StringFilter getOtros() {
        return otros;
    }

    public void setOtros(StringFilter otros) {
        this.otros = otros;
    }

    public LocalDateFilter getFechadeingreso() {
        return fechadeingreso;
    }

    public void setFechadeingreso(LocalDateFilter fechadeingreso) {
        this.fechadeingreso = fechadeingreso;
    }

    public StringFilter getInstrumentolegal() {
        return instrumentolegal;
    }

    public void setInstrumentolegal(StringFilter instrumentolegal) {
        this.instrumentolegal = instrumentolegal;
    }

    public StringFilter getCategoria() {
        return categoria;
    }

    public void setCategoria(StringFilter categoria) {
        this.categoria = categoria;
    }

    public StringFilter getItem() {
        return item;
    }

    public void setItem(StringFilter item) {
        this.item = item;
    }

    public StringFilter getPlanta() {
        return planta;
    }

    public void setPlanta(StringFilter planta) {
        this.planta = planta;
    }

    public StringFilter getArea() {
        return area;
    }

    public void setArea(StringFilter area) {
        this.area = area;
    }

    public StringFilter getDireccion() {
        return direccion;
    }

    public void setDireccion(StringFilter direccion) {
        this.direccion = direccion;
    }

    public IntegerFilter getAnnos() {
        return annos;
    }

    public void setAnnos(IntegerFilter annos) {
        this.annos = annos;
    }

    public IntegerFilter getMeses() {
        return meses;
    }

    public void setMeses(IntegerFilter meses) {
        this.meses = meses;
    }

    public IntegerFilter getDias() {
        return dias;
    }

    public void setDias(IntegerFilter dias) {
        this.dias = dias;
    }

    public StringFilter getRealizocomputodeservicios() {
        return realizocomputodeservicios;
    }

    public void setRealizocomputodeservicios(StringFilter realizocomputodeservicios) {
        this.realizocomputodeservicios = realizocomputodeservicios;
    }

    public StringFilter getPoseeconocimientoenmaquinasviales() {
        return poseeconocimientoenmaquinasviales;
    }

    public void setPoseeconocimientoenmaquinasviales(StringFilter poseeconocimientoenmaquinasviales) {
        this.poseeconocimientoenmaquinasviales = poseeconocimientoenmaquinasviales;
    }

    public StringFilter getCasoemergenciacelular() {
        return casoemergenciacelular;
    }

    public void setCasoemergenciacelular(StringFilter casoemergenciacelular) {
        this.casoemergenciacelular = casoemergenciacelular;
    }

    public StringFilter getCasoemergenciafijo() {
        return casoemergenciafijo;
    }

    public void setCasoemergenciafijo(StringFilter casoemergenciafijo) {
        this.casoemergenciafijo = casoemergenciafijo;
    }

    public StringFilter getCasoemergencianombre() {
        return casoemergencianombre;
    }

    public void setCasoemergencianombre(StringFilter casoemergencianombre) {
        this.casoemergencianombre = casoemergencianombre;
    }

    public StringFilter getCasoemergenciacelular2() {
        return casoemergenciacelular2;
    }

    public void setCasoemergenciacelular2(StringFilter casoemergenciacelular2) {
        this.casoemergenciacelular2 = casoemergenciacelular2;
    }

    public StringFilter getCasoemergenciafijo2() {
        return casoemergenciafijo2;
    }

    public void setCasoemergenciafijo2(StringFilter casoemergenciafijo2) {
        this.casoemergenciafijo2 = casoemergenciafijo2;
    }

    public StringFilter getCasoemergencianombre2() {
        return casoemergencianombre2;
    }

    public void setCasoemergencianombre2(StringFilter casoemergencianombre2) {
        this.casoemergencianombre2 = casoemergencianombre2;
    }

    public StringFilter getFamiliaracargonombre() {
        return familiaracargonombre;
    }

    public void setFamiliaracargonombre(StringFilter familiaracargonombre) {
        this.familiaracargonombre = familiaracargonombre;
    }

    public StringFilter getFamiliaracargonombre2() {
        return familiaracargonombre2;
    }

    public void setFamiliaracargonombre2(StringFilter familiaracargonombre2) {
        this.familiaracargonombre2 = familiaracargonombre2;
    }

    public StringFilter getFamiliaracargonombre3() {
        return familiaracargonombre3;
    }

    public void setFamiliaracargonombre3(StringFilter familiaracargonombre3) {
        this.familiaracargonombre3 = familiaracargonombre3;
    }

    public StringFilter getFamiliaracargonombre4() {
        return familiaracargonombre4;
    }

    public void setFamiliaracargonombre4(StringFilter familiaracargonombre4) {
        this.familiaracargonombre4 = familiaracargonombre4;
    }

    public StringFilter getFamiliaracargonombre5() {
        return familiaracargonombre5;
    }

    public void setFamiliaracargonombre5(StringFilter familiaracargonombre5) {
        this.familiaracargonombre5 = familiaracargonombre5;
    }

    public StringFilter getFamiliaracargodni() {
        return familiaracargodni;
    }

    public void setFamiliaracargodni(StringFilter familiaracargodni) {
        this.familiaracargodni = familiaracargodni;
    }

    public StringFilter getFamiliaracargodni2() {
        return familiaracargodni2;
    }

    public void setFamiliaracargodni2(StringFilter familiaracargodni2) {
        this.familiaracargodni2 = familiaracargodni2;
    }

    public StringFilter getFamiliaracargodni3() {
        return familiaracargodni3;
    }

    public void setFamiliaracargodni3(StringFilter familiaracargodni3) {
        this.familiaracargodni3 = familiaracargodni3;
    }

    public StringFilter getFamiliaracargodni4() {
        return familiaracargodni4;
    }

    public void setFamiliaracargodni4(StringFilter familiaracargodni4) {
        this.familiaracargodni4 = familiaracargodni4;
    }

    public StringFilter getFamiliaracargodni5() {
        return familiaracargodni5;
    }

    public void setFamiliaracargodni5(StringFilter familiaracargodni5) {
        this.familiaracargodni5 = familiaracargodni5;
    }

    public StringFilter getFamiliaracargoedad() {
        return familiaracargoedad;
    }

    public void setFamiliaracargoedad(StringFilter familiaracargoedad) {
        this.familiaracargoedad = familiaracargoedad;
    }

    public StringFilter getFamiliaracargoedad2() {
        return familiaracargoedad2;
    }

    public void setFamiliaracargoedad2(StringFilter familiaracargoedad2) {
        this.familiaracargoedad2 = familiaracargoedad2;
    }

    public StringFilter getFamiliaracargoedad3() {
        return familiaracargoedad3;
    }

    public void setFamiliaracargoedad3(StringFilter familiaracargoedad3) {
        this.familiaracargoedad3 = familiaracargoedad3;
    }

    public StringFilter getFamiliaracargoedad4() {
        return familiaracargoedad4;
    }

    public void setFamiliaracargoedad4(StringFilter familiaracargoedad4) {
        this.familiaracargoedad4 = familiaracargoedad4;
    }

    public StringFilter getFamiliaracargoedad5() {
        return familiaracargoedad5;
    }

    public void setFamiliaracargoedad5(StringFilter familiaracargoedad5) {
        this.familiaracargoedad5 = familiaracargoedad5;
    }

    public StringFilter getAltura() {
        return altura;
    }

    public void setAltura(StringFilter altura) {
        this.altura = altura;
    }

    public StringFilter getBarrio() {
        return barrio;
    }

    public void setBarrio(StringFilter barrio) {
        this.barrio = barrio;
    }

    public StringFilter getEstudiosincompletos() {
        return estudiosincompletos;
    }

    public void setEstudiosincompletos(StringFilter estudiosincompletos) {
        this.estudiosincompletos = estudiosincompletos;
    }

    public StringFilter getConyugeapellido() {
        return conyugeapellido;
    }

    public void setConyugeapellido(StringFilter conyugeapellido) {
        this.conyugeapellido = conyugeapellido;
    }

    public StringFilter getConyugenombre() {
        return conyugenombre;
    }

    public void setConyugenombre(StringFilter conyugenombre) {
        this.conyugenombre = conyugenombre;
    }

    public IntegerFilter getConyugedni() {
        return conyugedni;
    }

    public void setConyugedni(IntegerFilter conyugedni) {
        this.conyugedni = conyugedni;
    }

    public StringFilter getConyugecuil() {
        return conyugecuil;
    }

    public void setConyugecuil(StringFilter conyugecuil) {
        this.conyugecuil = conyugecuil;
    }

    public StringFilter getGrupofamiliarapellidonombre() {
        return grupofamiliarapellidonombre;
    }

    public void setGrupofamiliarapellidonombre(StringFilter grupofamiliarapellidonombre) {
        this.grupofamiliarapellidonombre = grupofamiliarapellidonombre;
    }

    public StringFilter getGrupofamiliarapellidonombre2() {
        return grupofamiliarapellidonombre2;
    }

    public void setGrupofamiliarapellidonombre2(StringFilter grupofamiliarapellidonombre2) {
        this.grupofamiliarapellidonombre2 = grupofamiliarapellidonombre2;
    }

    public StringFilter getGrupofamiliarapellidonombre3() {
        return grupofamiliarapellidonombre3;
    }

    public void setGrupofamiliarapellidonombre3(StringFilter grupofamiliarapellidonombre3) {
        this.grupofamiliarapellidonombre3 = grupofamiliarapellidonombre3;
    }

    public StringFilter getGrupofamiliarapellidonombre4() {
        return grupofamiliarapellidonombre4;
    }

    public void setGrupofamiliarapellidonombre4(StringFilter grupofamiliarapellidonombre4) {
        this.grupofamiliarapellidonombre4 = grupofamiliarapellidonombre4;
    }

    public StringFilter getGrupofamiliarapellidonombre5() {
        return grupofamiliarapellidonombre5;
    }

    public void setGrupofamiliarapellidonombre5(StringFilter grupofamiliarapellidonombre5) {
        this.grupofamiliarapellidonombre5 = grupofamiliarapellidonombre5;
    }

    public StringFilter getGrupofamiliarapellidonombre6() {
        return grupofamiliarapellidonombre6;
    }

    public void setGrupofamiliarapellidonombre6(StringFilter grupofamiliarapellidonombre6) {
        this.grupofamiliarapellidonombre6 = grupofamiliarapellidonombre6;
    }

    public StringFilter getGrupofamiliarapellidonombre7() {
        return grupofamiliarapellidonombre7;
    }

    public void setGrupofamiliarapellidonombre7(StringFilter grupofamiliarapellidonombre7) {
        this.grupofamiliarapellidonombre7 = grupofamiliarapellidonombre7;
    }

    public StringFilter getGrupofamiliarapellidonombre8() {
        return grupofamiliarapellidonombre8;
    }

    public void setGrupofamiliarapellidonombre8(StringFilter grupofamiliarapellidonombre8) {
        this.grupofamiliarapellidonombre8 = grupofamiliarapellidonombre8;
    }

    public StringFilter getGrupofamiliarapellidonombre9() {
        return grupofamiliarapellidonombre9;
    }

    public void setGrupofamiliarapellidonombre9(StringFilter grupofamiliarapellidonombre9) {
        this.grupofamiliarapellidonombre9 = grupofamiliarapellidonombre9;
    }

    public StringFilter getGrupofamiliarapellidonombre10() {
        return grupofamiliarapellidonombre10;
    }

    public void setGrupofamiliarapellidonombre10(StringFilter grupofamiliarapellidonombre10) {
        this.grupofamiliarapellidonombre10 = grupofamiliarapellidonombre10;
    }

    public StringFilter getGrupofamiliarapellidonombre11() {
        return grupofamiliarapellidonombre11;
    }

    public void setGrupofamiliarapellidonombre11(StringFilter grupofamiliarapellidonombre11) {
        this.grupofamiliarapellidonombre11 = grupofamiliarapellidonombre11;
    }

    public StringFilter getGrupofamiliarapellidonombreedad() {
        return grupofamiliarapellidonombreedad;
    }

    public void setGrupofamiliarapellidonombreedad(StringFilter grupofamiliarapellidonombreedad) {
        this.grupofamiliarapellidonombreedad = grupofamiliarapellidonombreedad;
    }

    public StringFilter getGrupofamiliarapellidonombreedad2() {
        return grupofamiliarapellidonombreedad2;
    }

    public void setGrupofamiliarapellidonombreedad2(StringFilter grupofamiliarapellidonombreedad2) {
        this.grupofamiliarapellidonombreedad2 = grupofamiliarapellidonombreedad2;
    }

    public StringFilter getGrupofamiliarapellidonombreedad3() {
        return grupofamiliarapellidonombreedad3;
    }

    public void setGrupofamiliarapellidonombreedad3(StringFilter grupofamiliarapellidonombreedad3) {
        this.grupofamiliarapellidonombreedad3 = grupofamiliarapellidonombreedad3;
    }

    public StringFilter getGrupofamiliarapellidonombreedad4() {
        return grupofamiliarapellidonombreedad4;
    }

    public void setGrupofamiliarapellidonombreedad4(StringFilter grupofamiliarapellidonombreedad4) {
        this.grupofamiliarapellidonombreedad4 = grupofamiliarapellidonombreedad4;
    }

    public StringFilter getGrupofamiliarapellidonombreedad5() {
        return grupofamiliarapellidonombreedad5;
    }

    public void setGrupofamiliarapellidonombreedad5(StringFilter grupofamiliarapellidonombreedad5) {
        this.grupofamiliarapellidonombreedad5 = grupofamiliarapellidonombreedad5;
    }

    public StringFilter getGrupofamiliarapellidonombreedad6() {
        return grupofamiliarapellidonombreedad6;
    }

    public void setGrupofamiliarapellidonombreedad6(StringFilter grupofamiliarapellidonombreedad6) {
        this.grupofamiliarapellidonombreedad6 = grupofamiliarapellidonombreedad6;
    }

    public StringFilter getGrupofamiliarapellidonombreedad7() {
        return grupofamiliarapellidonombreedad7;
    }

    public void setGrupofamiliarapellidonombreedad7(StringFilter grupofamiliarapellidonombreedad7) {
        this.grupofamiliarapellidonombreedad7 = grupofamiliarapellidonombreedad7;
    }

    public StringFilter getGrupofamiliarapellidonombreedad8() {
        return grupofamiliarapellidonombreedad8;
    }

    public void setGrupofamiliarapellidonombreedad8(StringFilter grupofamiliarapellidonombreedad8) {
        this.grupofamiliarapellidonombreedad8 = grupofamiliarapellidonombreedad8;
    }

    public StringFilter getGrupofamiliarapellidonombreedad9() {
        return grupofamiliarapellidonombreedad9;
    }

    public void setGrupofamiliarapellidonombreedad9(StringFilter grupofamiliarapellidonombreedad9) {
        this.grupofamiliarapellidonombreedad9 = grupofamiliarapellidonombreedad9;
    }

    public StringFilter getGrupofamiliarapellidonombreedad10() {
        return grupofamiliarapellidonombreedad10;
    }

    public void setGrupofamiliarapellidonombreedad10(StringFilter grupofamiliarapellidonombreedad10) {
        this.grupofamiliarapellidonombreedad10 = grupofamiliarapellidonombreedad10;
    }

    public StringFilter getGrupofamiliarapellidonombreedad11() {
        return grupofamiliarapellidonombreedad11;
    }

    public void setGrupofamiliarapellidonombreedad11(StringFilter grupofamiliarapellidonombreedad11) {
        this.grupofamiliarapellidonombreedad11 = grupofamiliarapellidonombreedad11;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni() {
        return grupofamiliarapellidonombredni;
    }

    public void setGrupofamiliarapellidonombredni(IntegerFilter grupofamiliarapellidonombredni) {
        this.grupofamiliarapellidonombredni = grupofamiliarapellidonombredni;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni2() {
        return grupofamiliarapellidonombredni2;
    }

    public void setGrupofamiliarapellidonombredni2(IntegerFilter grupofamiliarapellidonombredni2) {
        this.grupofamiliarapellidonombredni2 = grupofamiliarapellidonombredni2;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni3() {
        return grupofamiliarapellidonombredni3;
    }

    public void setGrupofamiliarapellidonombredni3(IntegerFilter grupofamiliarapellidonombredni3) {
        this.grupofamiliarapellidonombredni3 = grupofamiliarapellidonombredni3;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni4() {
        return grupofamiliarapellidonombredni4;
    }

    public void setGrupofamiliarapellidonombredni4(IntegerFilter grupofamiliarapellidonombredni4) {
        this.grupofamiliarapellidonombredni4 = grupofamiliarapellidonombredni4;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni5() {
        return grupofamiliarapellidonombredni5;
    }

    public void setGrupofamiliarapellidonombredni5(IntegerFilter grupofamiliarapellidonombredni5) {
        this.grupofamiliarapellidonombredni5 = grupofamiliarapellidonombredni5;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni6() {
        return grupofamiliarapellidonombredni6;
    }

    public void setGrupofamiliarapellidonombredni6(IntegerFilter grupofamiliarapellidonombredni6) {
        this.grupofamiliarapellidonombredni6 = grupofamiliarapellidonombredni6;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni7() {
        return grupofamiliarapellidonombredni7;
    }

    public void setGrupofamiliarapellidonombredni7(IntegerFilter grupofamiliarapellidonombredni7) {
        this.grupofamiliarapellidonombredni7 = grupofamiliarapellidonombredni7;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni8() {
        return grupofamiliarapellidonombredni8;
    }

    public void setGrupofamiliarapellidonombredni8(IntegerFilter grupofamiliarapellidonombredni8) {
        this.grupofamiliarapellidonombredni8 = grupofamiliarapellidonombredni8;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni9() {
        return grupofamiliarapellidonombredni9;
    }

    public void setGrupofamiliarapellidonombredni9(IntegerFilter grupofamiliarapellidonombredni9) {
        this.grupofamiliarapellidonombredni9 = grupofamiliarapellidonombredni9;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni10() {
        return grupofamiliarapellidonombredni10;
    }

    public void setGrupofamiliarapellidonombredni10(IntegerFilter grupofamiliarapellidonombredni10) {
        this.grupofamiliarapellidonombredni10 = grupofamiliarapellidonombredni10;
    }

    public IntegerFilter getGrupofamiliarapellidonombredni11() {
        return grupofamiliarapellidonombredni11;
    }

    public void setGrupofamiliarapellidonombredni11(IntegerFilter grupofamiliarapellidonombredni11) {
        this.grupofamiliarapellidonombredni11 = grupofamiliarapellidonombredni11;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar() {
        return grupofamiliarapellidonombrefamiliar;
    }

    public void setGrupofamiliarapellidonombrefamiliar(StringFilter grupofamiliarapellidonombrefamiliar) {
        this.grupofamiliarapellidonombrefamiliar = grupofamiliarapellidonombrefamiliar;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar2() {
        return grupofamiliarapellidonombrefamiliar2;
    }

    public void setGrupofamiliarapellidonombrefamiliar2(StringFilter grupofamiliarapellidonombrefamiliar2) {
        this.grupofamiliarapellidonombrefamiliar2 = grupofamiliarapellidonombrefamiliar2;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar4() {
        return grupofamiliarapellidonombrefamiliar4;
    }

    public void setGrupofamiliarapellidonombrefamiliar4(StringFilter grupofamiliarapellidonombrefamiliar4) {
        this.grupofamiliarapellidonombrefamiliar4 = grupofamiliarapellidonombrefamiliar4;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar3() {
        return grupofamiliarapellidonombrefamiliar3;
    }

    public void setGrupofamiliarapellidonombrefamiliar3(StringFilter grupofamiliarapellidonombrefamiliar3) {
        this.grupofamiliarapellidonombrefamiliar3 = grupofamiliarapellidonombrefamiliar3;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar5() {
        return grupofamiliarapellidonombrefamiliar5;
    }

    public void setGrupofamiliarapellidonombrefamiliar5(StringFilter grupofamiliarapellidonombrefamiliar5) {
        this.grupofamiliarapellidonombrefamiliar5 = grupofamiliarapellidonombrefamiliar5;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar6() {
        return grupofamiliarapellidonombrefamiliar6;
    }

    public void setGrupofamiliarapellidonombrefamiliar6(StringFilter grupofamiliarapellidonombrefamiliar6) {
        this.grupofamiliarapellidonombrefamiliar6 = grupofamiliarapellidonombrefamiliar6;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar7() {
        return grupofamiliarapellidonombrefamiliar7;
    }

    public void setGrupofamiliarapellidonombrefamiliar7(StringFilter grupofamiliarapellidonombrefamiliar7) {
        this.grupofamiliarapellidonombrefamiliar7 = grupofamiliarapellidonombrefamiliar7;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar8() {
        return grupofamiliarapellidonombrefamiliar8;
    }

    public void setGrupofamiliarapellidonombrefamiliar8(StringFilter grupofamiliarapellidonombrefamiliar8) {
        this.grupofamiliarapellidonombrefamiliar8 = grupofamiliarapellidonombrefamiliar8;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar9() {
        return grupofamiliarapellidonombrefamiliar9;
    }

    public void setGrupofamiliarapellidonombrefamiliar9(StringFilter grupofamiliarapellidonombrefamiliar9) {
        this.grupofamiliarapellidonombrefamiliar9 = grupofamiliarapellidonombrefamiliar9;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar10() {
        return grupofamiliarapellidonombrefamiliar10;
    }

    public void setGrupofamiliarapellidonombrefamiliar10(StringFilter grupofamiliarapellidonombrefamiliar10) {
        this.grupofamiliarapellidonombrefamiliar10 = grupofamiliarapellidonombrefamiliar10;
    }

    public StringFilter getGrupofamiliarapellidonombrefamiliar11() {
        return grupofamiliarapellidonombrefamiliar11;
    }

    public void setGrupofamiliarapellidonombrefamiliar11(StringFilter grupofamiliarapellidonombrefamiliar11) {
        this.grupofamiliarapellidonombrefamiliar11 = grupofamiliarapellidonombrefamiliar11;
    }

    public LongFilter getLicenciaId() {
        return licenciaId;
    }

    public void setLicenciaId(LongFilter licenciaId) {
        this.licenciaId = licenciaId;
    }

    public LongFilter getAltasAscensosBajasId() {
        return altasAscensosBajasId;
    }

    public void setAltasAscensosBajasId(LongFilter altasAscensosBajasId) {
        this.altasAscensosBajasId = altasAscensosBajasId;
    }

    public LongFilter getConcpremiosId() {
        return concpremiosId;
    }

    public void setConcpremiosId(LongFilter concpremiosId) {
        this.concpremiosId = concpremiosId;
    }

    public LongFilter getEmbargosId() {
        return embargosId;
    }

    public void setEmbargosId(LongFilter embargosId) {
        this.embargosId = embargosId;
    }

    public LongFilter getGarantiaId() {
        return garantiaId;
    }

    public void setGarantiaId(LongFilter garantiaId) {
        this.garantiaId = garantiaId;
    }

    public LongFilter getOtrosServiciosPrestadosId() {
        return otrosServiciosPrestadosId;
    }

    public void setOtrosServiciosPrestadosId(LongFilter otrosServiciosPrestadosId) {
        this.otrosServiciosPrestadosId = otrosServiciosPrestadosId;
    }

    public LongFilter getPenasDisciplinariasSufridasId() {
        return penasDisciplinariasSufridasId;
    }

    public void setPenasDisciplinariasSufridasId(LongFilter penasDisciplinariasSufridasId) {
        this.penasDisciplinariasSufridasId = penasDisciplinariasSufridasId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PersonaCriteria that = (PersonaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(apellido, that.apellido) &&
            Objects.equals(cuil, that.cuil) &&
            Objects.equals(dni, that.dni) &&
            Objects.equals(legajo, that.legajo) &&
            Objects.equals(apodo, that.apodo) &&
            Objects.equals(soltero, that.soltero) &&
            Objects.equals(casado, that.casado) &&
            Objects.equals(conviviente, that.conviviente) &&
            Objects.equals(viudo, that.viudo) &&
            Objects.equals(domicilio, that.domicilio) &&
            Objects.equals(lugar, that.lugar) &&
            Objects.equals(calle, that.calle) &&
            Objects.equals(numero, that.numero) &&
            Objects.equals(telefonofijo, that.telefonofijo) &&
            Objects.equals(numerodecelular, that.numerodecelular) &&
            Objects.equals(oficioprofecion, that.oficioprofecion) &&
            Objects.equals(niveldeestudios, that.niveldeestudios) &&
            Objects.equals(gruposanguineo, that.gruposanguineo) &&
            Objects.equals(factor, that.factor) &&
            Objects.equals(donante, that.donante) &&
            Objects.equals(diabetes, that.diabetes) &&
            Objects.equals(hipertension, that.hipertension) &&
            Objects.equals(alergias, that.alergias) &&
            Objects.equals(asma, that.asma) &&
            Objects.equals(otros, that.otros) &&
            Objects.equals(fechadeingreso, that.fechadeingreso) &&
            Objects.equals(instrumentolegal, that.instrumentolegal) &&
            Objects.equals(categoria, that.categoria) &&
            Objects.equals(item, that.item) &&
            Objects.equals(planta, that.planta) &&
            Objects.equals(area, that.area) &&
            Objects.equals(direccion, that.direccion) &&
            Objects.equals(annos, that.annos) &&
            Objects.equals(meses, that.meses) &&
            Objects.equals(dias, that.dias) &&
            Objects.equals(realizocomputodeservicios, that.realizocomputodeservicios) &&
            Objects.equals(poseeconocimientoenmaquinasviales, that.poseeconocimientoenmaquinasviales) &&
            Objects.equals(casoemergenciacelular, that.casoemergenciacelular) &&
            Objects.equals(casoemergenciafijo, that.casoemergenciafijo) &&
            Objects.equals(casoemergencianombre, that.casoemergencianombre) &&
            Objects.equals(casoemergenciacelular2, that.casoemergenciacelular2) &&
            Objects.equals(casoemergenciafijo2, that.casoemergenciafijo2) &&
            Objects.equals(casoemergencianombre2, that.casoemergencianombre2) &&
            Objects.equals(familiaracargonombre, that.familiaracargonombre) &&
            Objects.equals(familiaracargonombre2, that.familiaracargonombre2) &&
            Objects.equals(familiaracargonombre3, that.familiaracargonombre3) &&
            Objects.equals(familiaracargonombre4, that.familiaracargonombre4) &&
            Objects.equals(familiaracargonombre5, that.familiaracargonombre5) &&
            Objects.equals(familiaracargodni, that.familiaracargodni) &&
            Objects.equals(familiaracargodni2, that.familiaracargodni2) &&
            Objects.equals(familiaracargodni3, that.familiaracargodni3) &&
            Objects.equals(familiaracargodni4, that.familiaracargodni4) &&
            Objects.equals(familiaracargodni5, that.familiaracargodni5) &&
            Objects.equals(familiaracargoedad, that.familiaracargoedad) &&
            Objects.equals(familiaracargoedad2, that.familiaracargoedad2) &&
            Objects.equals(familiaracargoedad3, that.familiaracargoedad3) &&
            Objects.equals(familiaracargoedad4, that.familiaracargoedad4) &&
            Objects.equals(familiaracargoedad5, that.familiaracargoedad5) &&
            Objects.equals(altura, that.altura) &&
            Objects.equals(barrio, that.barrio) &&
            Objects.equals(estudiosincompletos, that.estudiosincompletos) &&
            Objects.equals(conyugeapellido, that.conyugeapellido) &&
            Objects.equals(conyugenombre, that.conyugenombre) &&
            Objects.equals(conyugedni, that.conyugedni) &&
            Objects.equals(conyugecuil, that.conyugecuil) &&
            Objects.equals(grupofamiliarapellidonombre, that.grupofamiliarapellidonombre) &&
            Objects.equals(grupofamiliarapellidonombre2, that.grupofamiliarapellidonombre2) &&
            Objects.equals(grupofamiliarapellidonombre3, that.grupofamiliarapellidonombre3) &&
            Objects.equals(grupofamiliarapellidonombre4, that.grupofamiliarapellidonombre4) &&
            Objects.equals(grupofamiliarapellidonombre5, that.grupofamiliarapellidonombre5) &&
            Objects.equals(grupofamiliarapellidonombre6, that.grupofamiliarapellidonombre6) &&
            Objects.equals(grupofamiliarapellidonombre7, that.grupofamiliarapellidonombre7) &&
            Objects.equals(grupofamiliarapellidonombre8, that.grupofamiliarapellidonombre8) &&
            Objects.equals(grupofamiliarapellidonombre9, that.grupofamiliarapellidonombre9) &&
            Objects.equals(grupofamiliarapellidonombre10, that.grupofamiliarapellidonombre10) &&
            Objects.equals(grupofamiliarapellidonombre11, that.grupofamiliarapellidonombre11) &&
            Objects.equals(grupofamiliarapellidonombreedad, that.grupofamiliarapellidonombreedad) &&
            Objects.equals(grupofamiliarapellidonombreedad2, that.grupofamiliarapellidonombreedad2) &&
            Objects.equals(grupofamiliarapellidonombreedad3, that.grupofamiliarapellidonombreedad3) &&
            Objects.equals(grupofamiliarapellidonombreedad4, that.grupofamiliarapellidonombreedad4) &&
            Objects.equals(grupofamiliarapellidonombreedad5, that.grupofamiliarapellidonombreedad5) &&
            Objects.equals(grupofamiliarapellidonombreedad6, that.grupofamiliarapellidonombreedad6) &&
            Objects.equals(grupofamiliarapellidonombreedad7, that.grupofamiliarapellidonombreedad7) &&
            Objects.equals(grupofamiliarapellidonombreedad8, that.grupofamiliarapellidonombreedad8) &&
            Objects.equals(grupofamiliarapellidonombreedad9, that.grupofamiliarapellidonombreedad9) &&
            Objects.equals(grupofamiliarapellidonombreedad10, that.grupofamiliarapellidonombreedad10) &&
            Objects.equals(grupofamiliarapellidonombreedad11, that.grupofamiliarapellidonombreedad11) &&
            Objects.equals(grupofamiliarapellidonombredni, that.grupofamiliarapellidonombredni) &&
            Objects.equals(grupofamiliarapellidonombredni2, that.grupofamiliarapellidonombredni2) &&
            Objects.equals(grupofamiliarapellidonombredni3, that.grupofamiliarapellidonombredni3) &&
            Objects.equals(grupofamiliarapellidonombredni4, that.grupofamiliarapellidonombredni4) &&
            Objects.equals(grupofamiliarapellidonombredni5, that.grupofamiliarapellidonombredni5) &&
            Objects.equals(grupofamiliarapellidonombredni6, that.grupofamiliarapellidonombredni6) &&
            Objects.equals(grupofamiliarapellidonombredni7, that.grupofamiliarapellidonombredni7) &&
            Objects.equals(grupofamiliarapellidonombredni8, that.grupofamiliarapellidonombredni8) &&
            Objects.equals(grupofamiliarapellidonombredni9, that.grupofamiliarapellidonombredni9) &&
            Objects.equals(grupofamiliarapellidonombredni10, that.grupofamiliarapellidonombredni10) &&
            Objects.equals(grupofamiliarapellidonombredni11, that.grupofamiliarapellidonombredni11) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar, that.grupofamiliarapellidonombrefamiliar) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar2, that.grupofamiliarapellidonombrefamiliar2) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar4, that.grupofamiliarapellidonombrefamiliar4) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar3, that.grupofamiliarapellidonombrefamiliar3) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar5, that.grupofamiliarapellidonombrefamiliar5) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar6, that.grupofamiliarapellidonombrefamiliar6) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar7, that.grupofamiliarapellidonombrefamiliar7) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar8, that.grupofamiliarapellidonombrefamiliar8) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar9, that.grupofamiliarapellidonombrefamiliar9) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar10, that.grupofamiliarapellidonombrefamiliar10) &&
            Objects.equals(grupofamiliarapellidonombrefamiliar11, that.grupofamiliarapellidonombrefamiliar11) &&
            Objects.equals(licenciaId, that.licenciaId) &&
            Objects.equals(altasAscensosBajasId, that.altasAscensosBajasId) &&
            Objects.equals(concpremiosId, that.concpremiosId) &&
            Objects.equals(embargosId, that.embargosId) &&
            Objects.equals(garantiaId, that.garantiaId) &&
            Objects.equals(otrosServiciosPrestadosId, that.otrosServiciosPrestadosId) &&
            Objects.equals(penasDisciplinariasSufridasId, that.penasDisciplinariasSufridasId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombre,
        apellido,
        cuil,
        dni,
        legajo,
        apodo,
        soltero,
        casado,
        conviviente,
        viudo,
        domicilio,
        lugar,
        calle,
        numero,
        telefonofijo,
        numerodecelular,
        oficioprofecion,
        niveldeestudios,
        gruposanguineo,
        factor,
        donante,
        diabetes,
        hipertension,
        alergias,
        asma,
        otros,
        fechadeingreso,
        instrumentolegal,
        categoria,
        item,
        planta,
        area,
        direccion,
        annos,
        meses,
        dias,
        realizocomputodeservicios,
        poseeconocimientoenmaquinasviales,
        casoemergenciacelular,
        casoemergenciafijo,
        casoemergencianombre,
        casoemergenciacelular2,
        casoemergenciafijo2,
        casoemergencianombre2,
        familiaracargonombre,
        familiaracargonombre2,
        familiaracargonombre3,
        familiaracargonombre4,
        familiaracargonombre5,
        familiaracargodni,
        familiaracargodni2,
        familiaracargodni3,
        familiaracargodni4,
        familiaracargodni5,
        familiaracargoedad,
        familiaracargoedad2,
        familiaracargoedad3,
        familiaracargoedad4,
        familiaracargoedad5,
        altura,
        barrio,
        estudiosincompletos,
        conyugeapellido,
        conyugenombre,
        conyugedni,
        conyugecuil,
        grupofamiliarapellidonombre,
        grupofamiliarapellidonombre2,
        grupofamiliarapellidonombre3,
        grupofamiliarapellidonombre4,
        grupofamiliarapellidonombre5,
        grupofamiliarapellidonombre6,
        grupofamiliarapellidonombre7,
        grupofamiliarapellidonombre8,
        grupofamiliarapellidonombre9,
        grupofamiliarapellidonombre10,
        grupofamiliarapellidonombre11,
        grupofamiliarapellidonombreedad,
        grupofamiliarapellidonombreedad2,
        grupofamiliarapellidonombreedad3,
        grupofamiliarapellidonombreedad4,
        grupofamiliarapellidonombreedad5,
        grupofamiliarapellidonombreedad6,
        grupofamiliarapellidonombreedad7,
        grupofamiliarapellidonombreedad8,
        grupofamiliarapellidonombreedad9,
        grupofamiliarapellidonombreedad10,
        grupofamiliarapellidonombreedad11,
        grupofamiliarapellidonombredni,
        grupofamiliarapellidonombredni2,
        grupofamiliarapellidonombredni3,
        grupofamiliarapellidonombredni4,
        grupofamiliarapellidonombredni5,
        grupofamiliarapellidonombredni6,
        grupofamiliarapellidonombredni7,
        grupofamiliarapellidonombredni8,
        grupofamiliarapellidonombredni9,
        grupofamiliarapellidonombredni10,
        grupofamiliarapellidonombredni11,
        grupofamiliarapellidonombrefamiliar,
        grupofamiliarapellidonombrefamiliar2,
        grupofamiliarapellidonombrefamiliar4,
        grupofamiliarapellidonombrefamiliar3,
        grupofamiliarapellidonombrefamiliar5,
        grupofamiliarapellidonombrefamiliar6,
        grupofamiliarapellidonombrefamiliar7,
        grupofamiliarapellidonombrefamiliar8,
        grupofamiliarapellidonombrefamiliar9,
        grupofamiliarapellidonombrefamiliar10,
        grupofamiliarapellidonombrefamiliar11,
        licenciaId,
        altasAscensosBajasId,
        concpremiosId,
        embargosId,
        garantiaId,
        otrosServiciosPrestadosId,
        penasDisciplinariasSufridasId
        );
    }

    @Override
    public String toString() {
        return "PersonaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (apellido != null ? "apellido=" + apellido + ", " : "") +
                (cuil != null ? "cuil=" + cuil + ", " : "") +
                (dni != null ? "dni=" + dni + ", " : "") +
                (legajo != null ? "legajo=" + legajo + ", " : "") +
                (apodo != null ? "apodo=" + apodo + ", " : "") +
                (soltero != null ? "soltero=" + soltero + ", " : "") +
                (casado != null ? "casado=" + casado + ", " : "") +
                (conviviente != null ? "conviviente=" + conviviente + ", " : "") +
                (viudo != null ? "viudo=" + viudo + ", " : "") +
                (domicilio != null ? "domicilio=" + domicilio + ", " : "") +
                (lugar != null ? "lugar=" + lugar + ", " : "") +
                (calle != null ? "calle=" + calle + ", " : "") +
                (numero != null ? "numero=" + numero + ", " : "") +
                (telefonofijo != null ? "telefonofijo=" + telefonofijo + ", " : "") +
                (numerodecelular != null ? "numerodecelular=" + numerodecelular + ", " : "") +
                (oficioprofecion != null ? "oficioprofecion=" + oficioprofecion + ", " : "") +
                (niveldeestudios != null ? "niveldeestudios=" + niveldeestudios + ", " : "") +
                (gruposanguineo != null ? "gruposanguineo=" + gruposanguineo + ", " : "") +
                (factor != null ? "factor=" + factor + ", " : "") +
                (donante != null ? "donante=" + donante + ", " : "") +
                (diabetes != null ? "diabetes=" + diabetes + ", " : "") +
                (hipertension != null ? "hipertension=" + hipertension + ", " : "") +
                (alergias != null ? "alergias=" + alergias + ", " : "") +
                (asma != null ? "asma=" + asma + ", " : "") +
                (otros != null ? "otros=" + otros + ", " : "") +
                (fechadeingreso != null ? "fechadeingreso=" + fechadeingreso + ", " : "") +
                (instrumentolegal != null ? "instrumentolegal=" + instrumentolegal + ", " : "") +
                (categoria != null ? "categoria=" + categoria + ", " : "") +
                (item != null ? "item=" + item + ", " : "") +
                (planta != null ? "planta=" + planta + ", " : "") +
                (area != null ? "area=" + area + ", " : "") +
                (direccion != null ? "direccion=" + direccion + ", " : "") +
                (annos != null ? "annos=" + annos + ", " : "") +
                (meses != null ? "meses=" + meses + ", " : "") +
                (dias != null ? "dias=" + dias + ", " : "") +
                (realizocomputodeservicios != null ? "realizocomputodeservicios=" + realizocomputodeservicios + ", " : "") +
                (poseeconocimientoenmaquinasviales != null ? "poseeconocimientoenmaquinasviales=" + poseeconocimientoenmaquinasviales + ", " : "") +
                (casoemergenciacelular != null ? "casoemergenciacelular=" + casoemergenciacelular + ", " : "") +
                (casoemergenciafijo != null ? "casoemergenciafijo=" + casoemergenciafijo + ", " : "") +
                (casoemergencianombre != null ? "casoemergencianombre=" + casoemergencianombre + ", " : "") +
                (casoemergenciacelular2 != null ? "casoemergenciacelular2=" + casoemergenciacelular2 + ", " : "") +
                (casoemergenciafijo2 != null ? "casoemergenciafijo2=" + casoemergenciafijo2 + ", " : "") +
                (casoemergencianombre2 != null ? "casoemergencianombre2=" + casoemergencianombre2 + ", " : "") +
                (familiaracargonombre != null ? "familiaracargonombre=" + familiaracargonombre + ", " : "") +
                (familiaracargonombre2 != null ? "familiaracargonombre2=" + familiaracargonombre2 + ", " : "") +
                (familiaracargonombre3 != null ? "familiaracargonombre3=" + familiaracargonombre3 + ", " : "") +
                (familiaracargonombre4 != null ? "familiaracargonombre4=" + familiaracargonombre4 + ", " : "") +
                (familiaracargonombre5 != null ? "familiaracargonombre5=" + familiaracargonombre5 + ", " : "") +
                (familiaracargodni != null ? "familiaracargodni=" + familiaracargodni + ", " : "") +
                (familiaracargodni2 != null ? "familiaracargodni2=" + familiaracargodni2 + ", " : "") +
                (familiaracargodni3 != null ? "familiaracargodni3=" + familiaracargodni3 + ", " : "") +
                (familiaracargodni4 != null ? "familiaracargodni4=" + familiaracargodni4 + ", " : "") +
                (familiaracargodni5 != null ? "familiaracargodni5=" + familiaracargodni5 + ", " : "") +
                (familiaracargoedad != null ? "familiaracargoedad=" + familiaracargoedad + ", " : "") +
                (familiaracargoedad2 != null ? "familiaracargoedad2=" + familiaracargoedad2 + ", " : "") +
                (familiaracargoedad3 != null ? "familiaracargoedad3=" + familiaracargoedad3 + ", " : "") +
                (familiaracargoedad4 != null ? "familiaracargoedad4=" + familiaracargoedad4 + ", " : "") +
                (familiaracargoedad5 != null ? "familiaracargoedad5=" + familiaracargoedad5 + ", " : "") +
                (altura != null ? "altura=" + altura + ", " : "") +
                (barrio != null ? "barrio=" + barrio + ", " : "") +
                (estudiosincompletos != null ? "estudiosincompletos=" + estudiosincompletos + ", " : "") +
                (conyugeapellido != null ? "conyugeapellido=" + conyugeapellido + ", " : "") +
                (conyugenombre != null ? "conyugenombre=" + conyugenombre + ", " : "") +
                (conyugedni != null ? "conyugedni=" + conyugedni + ", " : "") +
                (conyugecuil != null ? "conyugecuil=" + conyugecuil + ", " : "") +
                (grupofamiliarapellidonombre != null ? "grupofamiliarapellidonombre=" + grupofamiliarapellidonombre + ", " : "") +
                (grupofamiliarapellidonombre2 != null ? "grupofamiliarapellidonombre2=" + grupofamiliarapellidonombre2 + ", " : "") +
                (grupofamiliarapellidonombre3 != null ? "grupofamiliarapellidonombre3=" + grupofamiliarapellidonombre3 + ", " : "") +
                (grupofamiliarapellidonombre4 != null ? "grupofamiliarapellidonombre4=" + grupofamiliarapellidonombre4 + ", " : "") +
                (grupofamiliarapellidonombre5 != null ? "grupofamiliarapellidonombre5=" + grupofamiliarapellidonombre5 + ", " : "") +
                (grupofamiliarapellidonombre6 != null ? "grupofamiliarapellidonombre6=" + grupofamiliarapellidonombre6 + ", " : "") +
                (grupofamiliarapellidonombre7 != null ? "grupofamiliarapellidonombre7=" + grupofamiliarapellidonombre7 + ", " : "") +
                (grupofamiliarapellidonombre8 != null ? "grupofamiliarapellidonombre8=" + grupofamiliarapellidonombre8 + ", " : "") +
                (grupofamiliarapellidonombre9 != null ? "grupofamiliarapellidonombre9=" + grupofamiliarapellidonombre9 + ", " : "") +
                (grupofamiliarapellidonombre10 != null ? "grupofamiliarapellidonombre10=" + grupofamiliarapellidonombre10 + ", " : "") +
                (grupofamiliarapellidonombre11 != null ? "grupofamiliarapellidonombre11=" + grupofamiliarapellidonombre11 + ", " : "") +
                (grupofamiliarapellidonombreedad != null ? "grupofamiliarapellidonombreedad=" + grupofamiliarapellidonombreedad + ", " : "") +
                (grupofamiliarapellidonombreedad2 != null ? "grupofamiliarapellidonombreedad2=" + grupofamiliarapellidonombreedad2 + ", " : "") +
                (grupofamiliarapellidonombreedad3 != null ? "grupofamiliarapellidonombreedad3=" + grupofamiliarapellidonombreedad3 + ", " : "") +
                (grupofamiliarapellidonombreedad4 != null ? "grupofamiliarapellidonombreedad4=" + grupofamiliarapellidonombreedad4 + ", " : "") +
                (grupofamiliarapellidonombreedad5 != null ? "grupofamiliarapellidonombreedad5=" + grupofamiliarapellidonombreedad5 + ", " : "") +
                (grupofamiliarapellidonombreedad6 != null ? "grupofamiliarapellidonombreedad6=" + grupofamiliarapellidonombreedad6 + ", " : "") +
                (grupofamiliarapellidonombreedad7 != null ? "grupofamiliarapellidonombreedad7=" + grupofamiliarapellidonombreedad7 + ", " : "") +
                (grupofamiliarapellidonombreedad8 != null ? "grupofamiliarapellidonombreedad8=" + grupofamiliarapellidonombreedad8 + ", " : "") +
                (grupofamiliarapellidonombreedad9 != null ? "grupofamiliarapellidonombreedad9=" + grupofamiliarapellidonombreedad9 + ", " : "") +
                (grupofamiliarapellidonombreedad10 != null ? "grupofamiliarapellidonombreedad10=" + grupofamiliarapellidonombreedad10 + ", " : "") +
                (grupofamiliarapellidonombreedad11 != null ? "grupofamiliarapellidonombreedad11=" + grupofamiliarapellidonombreedad11 + ", " : "") +
                (grupofamiliarapellidonombredni != null ? "grupofamiliarapellidonombredni=" + grupofamiliarapellidonombredni + ", " : "") +
                (grupofamiliarapellidonombredni2 != null ? "grupofamiliarapellidonombredni2=" + grupofamiliarapellidonombredni2 + ", " : "") +
                (grupofamiliarapellidonombredni3 != null ? "grupofamiliarapellidonombredni3=" + grupofamiliarapellidonombredni3 + ", " : "") +
                (grupofamiliarapellidonombredni4 != null ? "grupofamiliarapellidonombredni4=" + grupofamiliarapellidonombredni4 + ", " : "") +
                (grupofamiliarapellidonombredni5 != null ? "grupofamiliarapellidonombredni5=" + grupofamiliarapellidonombredni5 + ", " : "") +
                (grupofamiliarapellidonombredni6 != null ? "grupofamiliarapellidonombredni6=" + grupofamiliarapellidonombredni6 + ", " : "") +
                (grupofamiliarapellidonombredni7 != null ? "grupofamiliarapellidonombredni7=" + grupofamiliarapellidonombredni7 + ", " : "") +
                (grupofamiliarapellidonombredni8 != null ? "grupofamiliarapellidonombredni8=" + grupofamiliarapellidonombredni8 + ", " : "") +
                (grupofamiliarapellidonombredni9 != null ? "grupofamiliarapellidonombredni9=" + grupofamiliarapellidonombredni9 + ", " : "") +
                (grupofamiliarapellidonombredni10 != null ? "grupofamiliarapellidonombredni10=" + grupofamiliarapellidonombredni10 + ", " : "") +
                (grupofamiliarapellidonombredni11 != null ? "grupofamiliarapellidonombredni11=" + grupofamiliarapellidonombredni11 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar != null ? "grupofamiliarapellidonombrefamiliar=" + grupofamiliarapellidonombrefamiliar + ", " : "") +
                (grupofamiliarapellidonombrefamiliar2 != null ? "grupofamiliarapellidonombrefamiliar2=" + grupofamiliarapellidonombrefamiliar2 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar4 != null ? "grupofamiliarapellidonombrefamiliar4=" + grupofamiliarapellidonombrefamiliar4 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar3 != null ? "grupofamiliarapellidonombrefamiliar3=" + grupofamiliarapellidonombrefamiliar3 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar5 != null ? "grupofamiliarapellidonombrefamiliar5=" + grupofamiliarapellidonombrefamiliar5 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar6 != null ? "grupofamiliarapellidonombrefamiliar6=" + grupofamiliarapellidonombrefamiliar6 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar7 != null ? "grupofamiliarapellidonombrefamiliar7=" + grupofamiliarapellidonombrefamiliar7 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar8 != null ? "grupofamiliarapellidonombrefamiliar8=" + grupofamiliarapellidonombrefamiliar8 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar9 != null ? "grupofamiliarapellidonombrefamiliar9=" + grupofamiliarapellidonombrefamiliar9 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar10 != null ? "grupofamiliarapellidonombrefamiliar10=" + grupofamiliarapellidonombrefamiliar10 + ", " : "") +
                (grupofamiliarapellidonombrefamiliar11 != null ? "grupofamiliarapellidonombrefamiliar11=" + grupofamiliarapellidonombrefamiliar11 + ", " : "") +
                (licenciaId != null ? "licenciaId=" + licenciaId + ", " : "") +
                (altasAscensosBajasId != null ? "altasAscensosBajasId=" + altasAscensosBajasId + ", " : "") +
                (concpremiosId != null ? "concpremiosId=" + concpremiosId + ", " : "") +
                (embargosId != null ? "embargosId=" + embargosId + ", " : "") +
                (garantiaId != null ? "garantiaId=" + garantiaId + ", " : "") +
                (otrosServiciosPrestadosId != null ? "otrosServiciosPrestadosId=" + otrosServiciosPrestadosId + ", " : "") +
                (penasDisciplinariasSufridasId != null ? "penasDisciplinariasSufridasId=" + penasDisciplinariasSufridasId + ", " : "") +
            "}";
    }

}

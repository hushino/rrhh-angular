package com.rrhh.client.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Persona.
 */
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotNull
    @Column(name = "cuil", nullable = false)
    private String cuil;

    @NotNull
    @Column(name = "dni", nullable = false)
    private Integer dni;

    @NotNull
    @Column(name = "legajo", nullable = false)
    private Integer legajo;

    @Column(name = "apodo")
    private String apodo;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @Column(name = "soltero")
    private String soltero;

    @Column(name = "casado")
    private String casado;

    @Column(name = "conviviente")
    private String conviviente;

    @Column(name = "viudo")
    private String viudo;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private String numero;

    @Column(name = "telefonofijo")
    private String telefonofijo;

    @Column(name = "numerodecelular")
    private String numerodecelular;

    @Column(name = "oficioprofecion")
    private String oficioprofecion;

    @Column(name = "niveldeestudios")
    private String niveldeestudios;

    @Column(name = "gruposanguineo")
    private String gruposanguineo;

    @Column(name = "factor")
    private String factor;

    @Column(name = "donante")
    private String donante;

    @Column(name = "diabetes")
    private String diabetes;

    @Column(name = "hipertension")
    private String hipertension;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "asma")
    private String asma;

    @Column(name = "otros")
    private String otros;

    @Column(name = "fechadeingreso")
    private LocalDate fechadeingreso;

    @Column(name = "instrumentolegal")
    private String instrumentolegal;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "item")
    private String item;

    @Column(name = "planta")
    private String planta;

    @Column(name = "area")
    private String area;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "annos")
    private Integer annos;

    @Column(name = "meses")
    private Integer meses;

    @Column(name = "dias")
    private Integer dias;

    @Column(name = "realizocomputodeservicios")
    private String realizocomputodeservicios;

    @Column(name = "poseeconocimientoenmaquinasviales")
    private String poseeconocimientoenmaquinasviales;

    @Column(name = "casoemergenciacelular")
    private String casoemergenciacelular;

    @Column(name = "casoemergenciafijo")
    private String casoemergenciafijo;

    @Column(name = "casoemergencianombre")
    private String casoemergencianombre;

    @Column(name = "casoemergenciacelular_2")
    private String casoemergenciacelular2;

    @Column(name = "casoemergenciafijo_2")
    private String casoemergenciafijo2;

    @Column(name = "casoemergencianombre_2")
    private String casoemergencianombre2;

    @Column(name = "familiaracargonombre")
    private String familiaracargonombre;

    @Column(name = "familiaracargonombre_2")
    private String familiaracargonombre2;

    @Column(name = "familiaracargonombre_3")
    private String familiaracargonombre3;

    @Column(name = "familiaracargonombre_4")
    private String familiaracargonombre4;

    @Column(name = "familiaracargonombre_5")
    private String familiaracargonombre5;

    @Column(name = "familiaracargodni")
    private String familiaracargodni;

    @Column(name = "familiaracargodni_2")
    private String familiaracargodni2;

    @Column(name = "familiaracargodni_3")
    private String familiaracargodni3;

    @Column(name = "familiaracargodni_4")
    private String familiaracargodni4;

    @Column(name = "familiaracargodni_5")
    private String familiaracargodni5;

    @Column(name = "familiaracargoedad")
    private String familiaracargoedad;

    @Column(name = "familiaracargoedad_2")
    private String familiaracargoedad2;

    @Column(name = "familiaracargoedad_3")
    private String familiaracargoedad3;

    @Column(name = "familiaracargoedad_4")
    private String familiaracargoedad4;

    @Column(name = "familiaracargoedad_5")
    private String familiaracargoedad5;

    @Column(name = "altura")
    private String altura;

    @Column(name = "barrio")
    private String barrio;

    @Column(name = "estudiosincompletos")
    private String estudiosincompletos;

    @Column(name = "conyugeapellido")
    private String conyugeapellido;

    @Column(name = "conyugenombre")
    private String conyugenombre;

    @Column(name = "conyugedni")
    private Integer conyugedni;

    @Column(name = "conyugecuil")
    private String conyugecuil;

    @Column(name = "grupofamiliarapellidonombre")
    private String grupofamiliarapellidonombre;

    @Column(name = "grupofamiliarapellidonombre_2")
    private String grupofamiliarapellidonombre2;

    @Column(name = "grupofamiliarapellidonombre_3")
    private String grupofamiliarapellidonombre3;

    @Column(name = "grupofamiliarapellidonombre_4")
    private String grupofamiliarapellidonombre4;

    @Column(name = "grupofamiliarapellidonombre_5")
    private String grupofamiliarapellidonombre5;

    @Column(name = "grupofamiliarapellidonombre_6")
    private String grupofamiliarapellidonombre6;

    @Column(name = "grupofamiliarapellidonombre_7")
    private String grupofamiliarapellidonombre7;

    @Column(name = "grupofamiliarapellidonombre_8")
    private String grupofamiliarapellidonombre8;

    @Column(name = "grupofamiliarapellidonombre_9")
    private String grupofamiliarapellidonombre9;

    @Column(name = "grupofamiliarapellidonombre_10")
    private String grupofamiliarapellidonombre10;

    @Column(name = "grupofamiliarapellidonombre_11")
    private String grupofamiliarapellidonombre11;

    @Column(name = "grupofamiliarapellidonombreedad")
    private String grupofamiliarapellidonombreedad;

    @Column(name = "grupofamiliarapellidonombreedad_2")
    private String grupofamiliarapellidonombreedad2;

    @Column(name = "grupofamiliarapellidonombreedad_3")
    private String grupofamiliarapellidonombreedad3;

    @Column(name = "grupofamiliarapellidonombreedad_4")
    private String grupofamiliarapellidonombreedad4;

    @Column(name = "grupofamiliarapellidonombreedad_5")
    private String grupofamiliarapellidonombreedad5;

    @Column(name = "grupofamiliarapellidonombreedad_6")
    private String grupofamiliarapellidonombreedad6;

    @Column(name = "grupofamiliarapellidonombreedad_7")
    private String grupofamiliarapellidonombreedad7;

    @Column(name = "grupofamiliarapellidonombreedad_8")
    private String grupofamiliarapellidonombreedad8;

    @Column(name = "grupofamiliarapellidonombreedad_9")
    private String grupofamiliarapellidonombreedad9;

    @Column(name = "grupofamiliarapellidonombreedad_10")
    private String grupofamiliarapellidonombreedad10;

    @Column(name = "grupofamiliarapellidonombreedad_11")
    private String grupofamiliarapellidonombreedad11;

    @Column(name = "grupofamiliarapellidonombredni")
    private Integer grupofamiliarapellidonombredni;

    @Column(name = "grupofamiliarapellidonombredni_2")
    private Integer grupofamiliarapellidonombredni2;

    @Column(name = "grupofamiliarapellidonombredni_3")
    private Integer grupofamiliarapellidonombredni3;

    @Column(name = "grupofamiliarapellidonombredni_4")
    private Integer grupofamiliarapellidonombredni4;

    @Column(name = "grupofamiliarapellidonombredni_5")
    private Integer grupofamiliarapellidonombredni5;

    @Column(name = "grupofamiliarapellidonombredni_6")
    private Integer grupofamiliarapellidonombredni6;

    @Column(name = "grupofamiliarapellidonombredni_7")
    private Integer grupofamiliarapellidonombredni7;

    @Column(name = "grupofamiliarapellidonombredni_8")
    private Integer grupofamiliarapellidonombredni8;

    @Column(name = "grupofamiliarapellidonombredni_9")
    private Integer grupofamiliarapellidonombredni9;

    @Column(name = "grupofamiliarapellidonombredni_10")
    private Integer grupofamiliarapellidonombredni10;

    @Column(name = "grupofamiliarapellidonombredni_11")
    private Integer grupofamiliarapellidonombredni11;

    @Column(name = "grupofamiliarapellidonombrefamiliar")
    private String grupofamiliarapellidonombrefamiliar;

    @Column(name = "grupofamiliarapellidonombrefamiliar_2")
    private String grupofamiliarapellidonombrefamiliar2;

    @Column(name = "grupofamiliarapellidonombrefamiliar_4")
    private String grupofamiliarapellidonombrefamiliar4;

    @Column(name = "grupofamiliarapellidonombrefamiliar_3")
    private String grupofamiliarapellidonombrefamiliar3;

    @Column(name = "grupofamiliarapellidonombrefamiliar_5")
    private String grupofamiliarapellidonombrefamiliar5;

    @Column(name = "grupofamiliarapellidonombrefamiliar_6")
    private String grupofamiliarapellidonombrefamiliar6;

    @Column(name = "grupofamiliarapellidonombrefamiliar_7")
    private String grupofamiliarapellidonombrefamiliar7;

    @Column(name = "grupofamiliarapellidonombrefamiliar_8")
    private String grupofamiliarapellidonombrefamiliar8;

    @Column(name = "grupofamiliarapellidonombrefamiliar_9")
    private String grupofamiliarapellidonombrefamiliar9;

    @Column(name = "grupofamiliarapellidonombrefamiliar_10")
    private String grupofamiliarapellidonombrefamiliar10;

    @Column(name = "grupofamiliarapellidonombrefamiliar_11")
    private String grupofamiliarapellidonombrefamiliar11;

    @OneToMany(mappedBy = "persona")
    private Set<Licencia> licencias = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    private Set<AltasAscensosBajas> altasAscensosBajas = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    private Set<Concpremios> concpremios = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    private Set<Embargos> embargos = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    private Set<Garantia> garantias = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    private Set<OtrosServiciosPrestados> otrosServiciosPrestados = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    private Set<PenasDisciplinariasSufridas> penasDisciplinariasSufridas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Persona nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Persona apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCuil() {
        return cuil;
    }

    public Persona cuil(String cuil) {
        this.cuil = cuil;
        return this;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Integer getDni() {
        return dni;
    }

    public Persona dni(Integer dni) {
        this.dni = dni;
        return this;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public Persona legajo(Integer legajo) {
        this.legajo = legajo;
        return this;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public String getApodo() {
        return apodo;
    }

    public Persona apodo(String apodo) {
        this.apodo = apodo;
        return this;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public Persona foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public Persona fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public String getSoltero() {
        return soltero;
    }

    public Persona soltero(String soltero) {
        this.soltero = soltero;
        return this;
    }

    public void setSoltero(String soltero) {
        this.soltero = soltero;
    }

    public String getCasado() {
        return casado;
    }

    public Persona casado(String casado) {
        this.casado = casado;
        return this;
    }

    public void setCasado(String casado) {
        this.casado = casado;
    }

    public String getConviviente() {
        return conviviente;
    }

    public Persona conviviente(String conviviente) {
        this.conviviente = conviviente;
        return this;
    }

    public void setConviviente(String conviviente) {
        this.conviviente = conviviente;
    }

    public String getViudo() {
        return viudo;
    }

    public Persona viudo(String viudo) {
        this.viudo = viudo;
        return this;
    }

    public void setViudo(String viudo) {
        this.viudo = viudo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Persona domicilio(String domicilio) {
        this.domicilio = domicilio;
        return this;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLugar() {
        return lugar;
    }

    public Persona lugar(String lugar) {
        this.lugar = lugar;
        return this;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCalle() {
        return calle;
    }

    public Persona calle(String calle) {
        this.calle = calle;
        return this;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public Persona numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefonofijo() {
        return telefonofijo;
    }

    public Persona telefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
        return this;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getNumerodecelular() {
        return numerodecelular;
    }

    public Persona numerodecelular(String numerodecelular) {
        this.numerodecelular = numerodecelular;
        return this;
    }

    public void setNumerodecelular(String numerodecelular) {
        this.numerodecelular = numerodecelular;
    }

    public String getOficioprofecion() {
        return oficioprofecion;
    }

    public Persona oficioprofecion(String oficioprofecion) {
        this.oficioprofecion = oficioprofecion;
        return this;
    }

    public void setOficioprofecion(String oficioprofecion) {
        this.oficioprofecion = oficioprofecion;
    }

    public String getNiveldeestudios() {
        return niveldeestudios;
    }

    public Persona niveldeestudios(String niveldeestudios) {
        this.niveldeestudios = niveldeestudios;
        return this;
    }

    public void setNiveldeestudios(String niveldeestudios) {
        this.niveldeestudios = niveldeestudios;
    }

    public String getGruposanguineo() {
        return gruposanguineo;
    }

    public Persona gruposanguineo(String gruposanguineo) {
        this.gruposanguineo = gruposanguineo;
        return this;
    }

    public void setGruposanguineo(String gruposanguineo) {
        this.gruposanguineo = gruposanguineo;
    }

    public String getFactor() {
        return factor;
    }

    public Persona factor(String factor) {
        this.factor = factor;
        return this;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getDonante() {
        return donante;
    }

    public Persona donante(String donante) {
        this.donante = donante;
        return this;
    }

    public void setDonante(String donante) {
        this.donante = donante;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public Persona diabetes(String diabetes) {
        this.diabetes = diabetes;
        return this;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getHipertension() {
        return hipertension;
    }

    public Persona hipertension(String hipertension) {
        this.hipertension = hipertension;
        return this;
    }

    public void setHipertension(String hipertension) {
        this.hipertension = hipertension;
    }

    public String getAlergias() {
        return alergias;
    }

    public Persona alergias(String alergias) {
        this.alergias = alergias;
        return this;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getAsma() {
        return asma;
    }

    public Persona asma(String asma) {
        this.asma = asma;
        return this;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public String getOtros() {
        return otros;
    }

    public Persona otros(String otros) {
        this.otros = otros;
        return this;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public LocalDate getFechadeingreso() {
        return fechadeingreso;
    }

    public Persona fechadeingreso(LocalDate fechadeingreso) {
        this.fechadeingreso = fechadeingreso;
        return this;
    }

    public void setFechadeingreso(LocalDate fechadeingreso) {
        this.fechadeingreso = fechadeingreso;
    }

    public String getInstrumentolegal() {
        return instrumentolegal;
    }

    public Persona instrumentolegal(String instrumentolegal) {
        this.instrumentolegal = instrumentolegal;
        return this;
    }

    public void setInstrumentolegal(String instrumentolegal) {
        this.instrumentolegal = instrumentolegal;
    }

    public String getCategoria() {
        return categoria;
    }

    public Persona categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getItem() {
        return item;
    }

    public Persona item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPlanta() {
        return planta;
    }

    public Persona planta(String planta) {
        this.planta = planta;
        return this;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getArea() {
        return area;
    }

    public Persona area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDireccion() {
        return direccion;
    }

    public Persona direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getAnnos() {
        return annos;
    }

    public Persona annos(Integer annos) {
        this.annos = annos;
        return this;
    }

    public void setAnnos(Integer annos) {
        this.annos = annos;
    }

    public Integer getMeses() {
        return meses;
    }

    public Persona meses(Integer meses) {
        this.meses = meses;
        return this;
    }

    public void setMeses(Integer meses) {
        this.meses = meses;
    }

    public Integer getDias() {
        return dias;
    }

    public Persona dias(Integer dias) {
        this.dias = dias;
        return this;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getRealizocomputodeservicios() {
        return realizocomputodeservicios;
    }

    public Persona realizocomputodeservicios(String realizocomputodeservicios) {
        this.realizocomputodeservicios = realizocomputodeservicios;
        return this;
    }

    public void setRealizocomputodeservicios(String realizocomputodeservicios) {
        this.realizocomputodeservicios = realizocomputodeservicios;
    }

    public String getPoseeconocimientoenmaquinasviales() {
        return poseeconocimientoenmaquinasviales;
    }

    public Persona poseeconocimientoenmaquinasviales(String poseeconocimientoenmaquinasviales) {
        this.poseeconocimientoenmaquinasviales = poseeconocimientoenmaquinasviales;
        return this;
    }

    public void setPoseeconocimientoenmaquinasviales(String poseeconocimientoenmaquinasviales) {
        this.poseeconocimientoenmaquinasviales = poseeconocimientoenmaquinasviales;
    }

    public String getCasoemergenciacelular() {
        return casoemergenciacelular;
    }

    public Persona casoemergenciacelular(String casoemergenciacelular) {
        this.casoemergenciacelular = casoemergenciacelular;
        return this;
    }

    public void setCasoemergenciacelular(String casoemergenciacelular) {
        this.casoemergenciacelular = casoemergenciacelular;
    }

    public String getCasoemergenciafijo() {
        return casoemergenciafijo;
    }

    public Persona casoemergenciafijo(String casoemergenciafijo) {
        this.casoemergenciafijo = casoemergenciafijo;
        return this;
    }

    public void setCasoemergenciafijo(String casoemergenciafijo) {
        this.casoemergenciafijo = casoemergenciafijo;
    }

    public String getCasoemergencianombre() {
        return casoemergencianombre;
    }

    public Persona casoemergencianombre(String casoemergencianombre) {
        this.casoemergencianombre = casoemergencianombre;
        return this;
    }

    public void setCasoemergencianombre(String casoemergencianombre) {
        this.casoemergencianombre = casoemergencianombre;
    }

    public String getCasoemergenciacelular2() {
        return casoemergenciacelular2;
    }

    public Persona casoemergenciacelular2(String casoemergenciacelular2) {
        this.casoemergenciacelular2 = casoemergenciacelular2;
        return this;
    }

    public void setCasoemergenciacelular2(String casoemergenciacelular2) {
        this.casoemergenciacelular2 = casoemergenciacelular2;
    }

    public String getCasoemergenciafijo2() {
        return casoemergenciafijo2;
    }

    public Persona casoemergenciafijo2(String casoemergenciafijo2) {
        this.casoemergenciafijo2 = casoemergenciafijo2;
        return this;
    }

    public void setCasoemergenciafijo2(String casoemergenciafijo2) {
        this.casoemergenciafijo2 = casoemergenciafijo2;
    }

    public String getCasoemergencianombre2() {
        return casoemergencianombre2;
    }

    public Persona casoemergencianombre2(String casoemergencianombre2) {
        this.casoemergencianombre2 = casoemergencianombre2;
        return this;
    }

    public void setCasoemergencianombre2(String casoemergencianombre2) {
        this.casoemergencianombre2 = casoemergencianombre2;
    }

    public String getFamiliaracargonombre() {
        return familiaracargonombre;
    }

    public Persona familiaracargonombre(String familiaracargonombre) {
        this.familiaracargonombre = familiaracargonombre;
        return this;
    }

    public void setFamiliaracargonombre(String familiaracargonombre) {
        this.familiaracargonombre = familiaracargonombre;
    }

    public String getFamiliaracargonombre2() {
        return familiaracargonombre2;
    }

    public Persona familiaracargonombre2(String familiaracargonombre2) {
        this.familiaracargonombre2 = familiaracargonombre2;
        return this;
    }

    public void setFamiliaracargonombre2(String familiaracargonombre2) {
        this.familiaracargonombre2 = familiaracargonombre2;
    }

    public String getFamiliaracargonombre3() {
        return familiaracargonombre3;
    }

    public Persona familiaracargonombre3(String familiaracargonombre3) {
        this.familiaracargonombre3 = familiaracargonombre3;
        return this;
    }

    public void setFamiliaracargonombre3(String familiaracargonombre3) {
        this.familiaracargonombre3 = familiaracargonombre3;
    }

    public String getFamiliaracargonombre4() {
        return familiaracargonombre4;
    }

    public Persona familiaracargonombre4(String familiaracargonombre4) {
        this.familiaracargonombre4 = familiaracargonombre4;
        return this;
    }

    public void setFamiliaracargonombre4(String familiaracargonombre4) {
        this.familiaracargonombre4 = familiaracargonombre4;
    }

    public String getFamiliaracargonombre5() {
        return familiaracargonombre5;
    }

    public Persona familiaracargonombre5(String familiaracargonombre5) {
        this.familiaracargonombre5 = familiaracargonombre5;
        return this;
    }

    public void setFamiliaracargonombre5(String familiaracargonombre5) {
        this.familiaracargonombre5 = familiaracargonombre5;
    }

    public String getFamiliaracargodni() {
        return familiaracargodni;
    }

    public Persona familiaracargodni(String familiaracargodni) {
        this.familiaracargodni = familiaracargodni;
        return this;
    }

    public void setFamiliaracargodni(String familiaracargodni) {
        this.familiaracargodni = familiaracargodni;
    }

    public String getFamiliaracargodni2() {
        return familiaracargodni2;
    }

    public Persona familiaracargodni2(String familiaracargodni2) {
        this.familiaracargodni2 = familiaracargodni2;
        return this;
    }

    public void setFamiliaracargodni2(String familiaracargodni2) {
        this.familiaracargodni2 = familiaracargodni2;
    }

    public String getFamiliaracargodni3() {
        return familiaracargodni3;
    }

    public Persona familiaracargodni3(String familiaracargodni3) {
        this.familiaracargodni3 = familiaracargodni3;
        return this;
    }

    public void setFamiliaracargodni3(String familiaracargodni3) {
        this.familiaracargodni3 = familiaracargodni3;
    }

    public String getFamiliaracargodni4() {
        return familiaracargodni4;
    }

    public Persona familiaracargodni4(String familiaracargodni4) {
        this.familiaracargodni4 = familiaracargodni4;
        return this;
    }

    public void setFamiliaracargodni4(String familiaracargodni4) {
        this.familiaracargodni4 = familiaracargodni4;
    }

    public String getFamiliaracargodni5() {
        return familiaracargodni5;
    }

    public Persona familiaracargodni5(String familiaracargodni5) {
        this.familiaracargodni5 = familiaracargodni5;
        return this;
    }

    public void setFamiliaracargodni5(String familiaracargodni5) {
        this.familiaracargodni5 = familiaracargodni5;
    }

    public String getFamiliaracargoedad() {
        return familiaracargoedad;
    }

    public Persona familiaracargoedad(String familiaracargoedad) {
        this.familiaracargoedad = familiaracargoedad;
        return this;
    }

    public void setFamiliaracargoedad(String familiaracargoedad) {
        this.familiaracargoedad = familiaracargoedad;
    }

    public String getFamiliaracargoedad2() {
        return familiaracargoedad2;
    }

    public Persona familiaracargoedad2(String familiaracargoedad2) {
        this.familiaracargoedad2 = familiaracargoedad2;
        return this;
    }

    public void setFamiliaracargoedad2(String familiaracargoedad2) {
        this.familiaracargoedad2 = familiaracargoedad2;
    }

    public String getFamiliaracargoedad3() {
        return familiaracargoedad3;
    }

    public Persona familiaracargoedad3(String familiaracargoedad3) {
        this.familiaracargoedad3 = familiaracargoedad3;
        return this;
    }

    public void setFamiliaracargoedad3(String familiaracargoedad3) {
        this.familiaracargoedad3 = familiaracargoedad3;
    }

    public String getFamiliaracargoedad4() {
        return familiaracargoedad4;
    }

    public Persona familiaracargoedad4(String familiaracargoedad4) {
        this.familiaracargoedad4 = familiaracargoedad4;
        return this;
    }

    public void setFamiliaracargoedad4(String familiaracargoedad4) {
        this.familiaracargoedad4 = familiaracargoedad4;
    }

    public String getFamiliaracargoedad5() {
        return familiaracargoedad5;
    }

    public Persona familiaracargoedad5(String familiaracargoedad5) {
        this.familiaracargoedad5 = familiaracargoedad5;
        return this;
    }

    public void setFamiliaracargoedad5(String familiaracargoedad5) {
        this.familiaracargoedad5 = familiaracargoedad5;
    }

    public String getAltura() {
        return altura;
    }

    public Persona altura(String altura) {
        this.altura = altura;
        return this;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getBarrio() {
        return barrio;
    }

    public Persona barrio(String barrio) {
        this.barrio = barrio;
        return this;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getEstudiosincompletos() {
        return estudiosincompletos;
    }

    public Persona estudiosincompletos(String estudiosincompletos) {
        this.estudiosincompletos = estudiosincompletos;
        return this;
    }

    public void setEstudiosincompletos(String estudiosincompletos) {
        this.estudiosincompletos = estudiosincompletos;
    }

    public String getConyugeapellido() {
        return conyugeapellido;
    }

    public Persona conyugeapellido(String conyugeapellido) {
        this.conyugeapellido = conyugeapellido;
        return this;
    }

    public void setConyugeapellido(String conyugeapellido) {
        this.conyugeapellido = conyugeapellido;
    }

    public String getConyugenombre() {
        return conyugenombre;
    }

    public Persona conyugenombre(String conyugenombre) {
        this.conyugenombre = conyugenombre;
        return this;
    }

    public void setConyugenombre(String conyugenombre) {
        this.conyugenombre = conyugenombre;
    }

    public Integer getConyugedni() {
        return conyugedni;
    }

    public Persona conyugedni(Integer conyugedni) {
        this.conyugedni = conyugedni;
        return this;
    }

    public void setConyugedni(Integer conyugedni) {
        this.conyugedni = conyugedni;
    }

    public String getConyugecuil() {
        return conyugecuil;
    }

    public Persona conyugecuil(String conyugecuil) {
        this.conyugecuil = conyugecuil;
        return this;
    }

    public void setConyugecuil(String conyugecuil) {
        this.conyugecuil = conyugecuil;
    }

    public String getGrupofamiliarapellidonombre() {
        return grupofamiliarapellidonombre;
    }

    public Persona grupofamiliarapellidonombre(String grupofamiliarapellidonombre) {
        this.grupofamiliarapellidonombre = grupofamiliarapellidonombre;
        return this;
    }

    public void setGrupofamiliarapellidonombre(String grupofamiliarapellidonombre) {
        this.grupofamiliarapellidonombre = grupofamiliarapellidonombre;
    }

    public String getGrupofamiliarapellidonombre2() {
        return grupofamiliarapellidonombre2;
    }

    public Persona grupofamiliarapellidonombre2(String grupofamiliarapellidonombre2) {
        this.grupofamiliarapellidonombre2 = grupofamiliarapellidonombre2;
        return this;
    }

    public void setGrupofamiliarapellidonombre2(String grupofamiliarapellidonombre2) {
        this.grupofamiliarapellidonombre2 = grupofamiliarapellidonombre2;
    }

    public String getGrupofamiliarapellidonombre3() {
        return grupofamiliarapellidonombre3;
    }

    public Persona grupofamiliarapellidonombre3(String grupofamiliarapellidonombre3) {
        this.grupofamiliarapellidonombre3 = grupofamiliarapellidonombre3;
        return this;
    }

    public void setGrupofamiliarapellidonombre3(String grupofamiliarapellidonombre3) {
        this.grupofamiliarapellidonombre3 = grupofamiliarapellidonombre3;
    }

    public String getGrupofamiliarapellidonombre4() {
        return grupofamiliarapellidonombre4;
    }

    public Persona grupofamiliarapellidonombre4(String grupofamiliarapellidonombre4) {
        this.grupofamiliarapellidonombre4 = grupofamiliarapellidonombre4;
        return this;
    }

    public void setGrupofamiliarapellidonombre4(String grupofamiliarapellidonombre4) {
        this.grupofamiliarapellidonombre4 = grupofamiliarapellidonombre4;
    }

    public String getGrupofamiliarapellidonombre5() {
        return grupofamiliarapellidonombre5;
    }

    public Persona grupofamiliarapellidonombre5(String grupofamiliarapellidonombre5) {
        this.grupofamiliarapellidonombre5 = grupofamiliarapellidonombre5;
        return this;
    }

    public void setGrupofamiliarapellidonombre5(String grupofamiliarapellidonombre5) {
        this.grupofamiliarapellidonombre5 = grupofamiliarapellidonombre5;
    }

    public String getGrupofamiliarapellidonombre6() {
        return grupofamiliarapellidonombre6;
    }

    public Persona grupofamiliarapellidonombre6(String grupofamiliarapellidonombre6) {
        this.grupofamiliarapellidonombre6 = grupofamiliarapellidonombre6;
        return this;
    }

    public void setGrupofamiliarapellidonombre6(String grupofamiliarapellidonombre6) {
        this.grupofamiliarapellidonombre6 = grupofamiliarapellidonombre6;
    }

    public String getGrupofamiliarapellidonombre7() {
        return grupofamiliarapellidonombre7;
    }

    public Persona grupofamiliarapellidonombre7(String grupofamiliarapellidonombre7) {
        this.grupofamiliarapellidonombre7 = grupofamiliarapellidonombre7;
        return this;
    }

    public void setGrupofamiliarapellidonombre7(String grupofamiliarapellidonombre7) {
        this.grupofamiliarapellidonombre7 = grupofamiliarapellidonombre7;
    }

    public String getGrupofamiliarapellidonombre8() {
        return grupofamiliarapellidonombre8;
    }

    public Persona grupofamiliarapellidonombre8(String grupofamiliarapellidonombre8) {
        this.grupofamiliarapellidonombre8 = grupofamiliarapellidonombre8;
        return this;
    }

    public void setGrupofamiliarapellidonombre8(String grupofamiliarapellidonombre8) {
        this.grupofamiliarapellidonombre8 = grupofamiliarapellidonombre8;
    }

    public String getGrupofamiliarapellidonombre9() {
        return grupofamiliarapellidonombre9;
    }

    public Persona grupofamiliarapellidonombre9(String grupofamiliarapellidonombre9) {
        this.grupofamiliarapellidonombre9 = grupofamiliarapellidonombre9;
        return this;
    }

    public void setGrupofamiliarapellidonombre9(String grupofamiliarapellidonombre9) {
        this.grupofamiliarapellidonombre9 = grupofamiliarapellidonombre9;
    }

    public String getGrupofamiliarapellidonombre10() {
        return grupofamiliarapellidonombre10;
    }

    public Persona grupofamiliarapellidonombre10(String grupofamiliarapellidonombre10) {
        this.grupofamiliarapellidonombre10 = grupofamiliarapellidonombre10;
        return this;
    }

    public void setGrupofamiliarapellidonombre10(String grupofamiliarapellidonombre10) {
        this.grupofamiliarapellidonombre10 = grupofamiliarapellidonombre10;
    }

    public String getGrupofamiliarapellidonombre11() {
        return grupofamiliarapellidonombre11;
    }

    public Persona grupofamiliarapellidonombre11(String grupofamiliarapellidonombre11) {
        this.grupofamiliarapellidonombre11 = grupofamiliarapellidonombre11;
        return this;
    }

    public void setGrupofamiliarapellidonombre11(String grupofamiliarapellidonombre11) {
        this.grupofamiliarapellidonombre11 = grupofamiliarapellidonombre11;
    }

    public String getGrupofamiliarapellidonombreedad() {
        return grupofamiliarapellidonombreedad;
    }

    public Persona grupofamiliarapellidonombreedad(String grupofamiliarapellidonombreedad) {
        this.grupofamiliarapellidonombreedad = grupofamiliarapellidonombreedad;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad(String grupofamiliarapellidonombreedad) {
        this.grupofamiliarapellidonombreedad = grupofamiliarapellidonombreedad;
    }

    public String getGrupofamiliarapellidonombreedad2() {
        return grupofamiliarapellidonombreedad2;
    }

    public Persona grupofamiliarapellidonombreedad2(String grupofamiliarapellidonombreedad2) {
        this.grupofamiliarapellidonombreedad2 = grupofamiliarapellidonombreedad2;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad2(String grupofamiliarapellidonombreedad2) {
        this.grupofamiliarapellidonombreedad2 = grupofamiliarapellidonombreedad2;
    }

    public String getGrupofamiliarapellidonombreedad3() {
        return grupofamiliarapellidonombreedad3;
    }

    public Persona grupofamiliarapellidonombreedad3(String grupofamiliarapellidonombreedad3) {
        this.grupofamiliarapellidonombreedad3 = grupofamiliarapellidonombreedad3;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad3(String grupofamiliarapellidonombreedad3) {
        this.grupofamiliarapellidonombreedad3 = grupofamiliarapellidonombreedad3;
    }

    public String getGrupofamiliarapellidonombreedad4() {
        return grupofamiliarapellidonombreedad4;
    }

    public Persona grupofamiliarapellidonombreedad4(String grupofamiliarapellidonombreedad4) {
        this.grupofamiliarapellidonombreedad4 = grupofamiliarapellidonombreedad4;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad4(String grupofamiliarapellidonombreedad4) {
        this.grupofamiliarapellidonombreedad4 = grupofamiliarapellidonombreedad4;
    }

    public String getGrupofamiliarapellidonombreedad5() {
        return grupofamiliarapellidonombreedad5;
    }

    public Persona grupofamiliarapellidonombreedad5(String grupofamiliarapellidonombreedad5) {
        this.grupofamiliarapellidonombreedad5 = grupofamiliarapellidonombreedad5;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad5(String grupofamiliarapellidonombreedad5) {
        this.grupofamiliarapellidonombreedad5 = grupofamiliarapellidonombreedad5;
    }

    public String getGrupofamiliarapellidonombreedad6() {
        return grupofamiliarapellidonombreedad6;
    }

    public Persona grupofamiliarapellidonombreedad6(String grupofamiliarapellidonombreedad6) {
        this.grupofamiliarapellidonombreedad6 = grupofamiliarapellidonombreedad6;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad6(String grupofamiliarapellidonombreedad6) {
        this.grupofamiliarapellidonombreedad6 = grupofamiliarapellidonombreedad6;
    }

    public String getGrupofamiliarapellidonombreedad7() {
        return grupofamiliarapellidonombreedad7;
    }

    public Persona grupofamiliarapellidonombreedad7(String grupofamiliarapellidonombreedad7) {
        this.grupofamiliarapellidonombreedad7 = grupofamiliarapellidonombreedad7;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad7(String grupofamiliarapellidonombreedad7) {
        this.grupofamiliarapellidonombreedad7 = grupofamiliarapellidonombreedad7;
    }

    public String getGrupofamiliarapellidonombreedad8() {
        return grupofamiliarapellidonombreedad8;
    }

    public Persona grupofamiliarapellidonombreedad8(String grupofamiliarapellidonombreedad8) {
        this.grupofamiliarapellidonombreedad8 = grupofamiliarapellidonombreedad8;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad8(String grupofamiliarapellidonombreedad8) {
        this.grupofamiliarapellidonombreedad8 = grupofamiliarapellidonombreedad8;
    }

    public String getGrupofamiliarapellidonombreedad9() {
        return grupofamiliarapellidonombreedad9;
    }

    public Persona grupofamiliarapellidonombreedad9(String grupofamiliarapellidonombreedad9) {
        this.grupofamiliarapellidonombreedad9 = grupofamiliarapellidonombreedad9;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad9(String grupofamiliarapellidonombreedad9) {
        this.grupofamiliarapellidonombreedad9 = grupofamiliarapellidonombreedad9;
    }

    public String getGrupofamiliarapellidonombreedad10() {
        return grupofamiliarapellidonombreedad10;
    }

    public Persona grupofamiliarapellidonombreedad10(String grupofamiliarapellidonombreedad10) {
        this.grupofamiliarapellidonombreedad10 = grupofamiliarapellidonombreedad10;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad10(String grupofamiliarapellidonombreedad10) {
        this.grupofamiliarapellidonombreedad10 = grupofamiliarapellidonombreedad10;
    }

    public String getGrupofamiliarapellidonombreedad11() {
        return grupofamiliarapellidonombreedad11;
    }

    public Persona grupofamiliarapellidonombreedad11(String grupofamiliarapellidonombreedad11) {
        this.grupofamiliarapellidonombreedad11 = grupofamiliarapellidonombreedad11;
        return this;
    }

    public void setGrupofamiliarapellidonombreedad11(String grupofamiliarapellidonombreedad11) {
        this.grupofamiliarapellidonombreedad11 = grupofamiliarapellidonombreedad11;
    }

    public Integer getGrupofamiliarapellidonombredni() {
        return grupofamiliarapellidonombredni;
    }

    public Persona grupofamiliarapellidonombredni(Integer grupofamiliarapellidonombredni) {
        this.grupofamiliarapellidonombredni = grupofamiliarapellidonombredni;
        return this;
    }

    public void setGrupofamiliarapellidonombredni(Integer grupofamiliarapellidonombredni) {
        this.grupofamiliarapellidonombredni = grupofamiliarapellidonombredni;
    }

    public Integer getGrupofamiliarapellidonombredni2() {
        return grupofamiliarapellidonombredni2;
    }

    public Persona grupofamiliarapellidonombredni2(Integer grupofamiliarapellidonombredni2) {
        this.grupofamiliarapellidonombredni2 = grupofamiliarapellidonombredni2;
        return this;
    }

    public void setGrupofamiliarapellidonombredni2(Integer grupofamiliarapellidonombredni2) {
        this.grupofamiliarapellidonombredni2 = grupofamiliarapellidonombredni2;
    }

    public Integer getGrupofamiliarapellidonombredni3() {
        return grupofamiliarapellidonombredni3;
    }

    public Persona grupofamiliarapellidonombredni3(Integer grupofamiliarapellidonombredni3) {
        this.grupofamiliarapellidonombredni3 = grupofamiliarapellidonombredni3;
        return this;
    }

    public void setGrupofamiliarapellidonombredni3(Integer grupofamiliarapellidonombredni3) {
        this.grupofamiliarapellidonombredni3 = grupofamiliarapellidonombredni3;
    }

    public Integer getGrupofamiliarapellidonombredni4() {
        return grupofamiliarapellidonombredni4;
    }

    public Persona grupofamiliarapellidonombredni4(Integer grupofamiliarapellidonombredni4) {
        this.grupofamiliarapellidonombredni4 = grupofamiliarapellidonombredni4;
        return this;
    }

    public void setGrupofamiliarapellidonombredni4(Integer grupofamiliarapellidonombredni4) {
        this.grupofamiliarapellidonombredni4 = grupofamiliarapellidonombredni4;
    }

    public Integer getGrupofamiliarapellidonombredni5() {
        return grupofamiliarapellidonombredni5;
    }

    public Persona grupofamiliarapellidonombredni5(Integer grupofamiliarapellidonombredni5) {
        this.grupofamiliarapellidonombredni5 = grupofamiliarapellidonombredni5;
        return this;
    }

    public void setGrupofamiliarapellidonombredni5(Integer grupofamiliarapellidonombredni5) {
        this.grupofamiliarapellidonombredni5 = grupofamiliarapellidonombredni5;
    }

    public Integer getGrupofamiliarapellidonombredni6() {
        return grupofamiliarapellidonombredni6;
    }

    public Persona grupofamiliarapellidonombredni6(Integer grupofamiliarapellidonombredni6) {
        this.grupofamiliarapellidonombredni6 = grupofamiliarapellidonombredni6;
        return this;
    }

    public void setGrupofamiliarapellidonombredni6(Integer grupofamiliarapellidonombredni6) {
        this.grupofamiliarapellidonombredni6 = grupofamiliarapellidonombredni6;
    }

    public Integer getGrupofamiliarapellidonombredni7() {
        return grupofamiliarapellidonombredni7;
    }

    public Persona grupofamiliarapellidonombredni7(Integer grupofamiliarapellidonombredni7) {
        this.grupofamiliarapellidonombredni7 = grupofamiliarapellidonombredni7;
        return this;
    }

    public void setGrupofamiliarapellidonombredni7(Integer grupofamiliarapellidonombredni7) {
        this.grupofamiliarapellidonombredni7 = grupofamiliarapellidonombredni7;
    }

    public Integer getGrupofamiliarapellidonombredni8() {
        return grupofamiliarapellidonombredni8;
    }

    public Persona grupofamiliarapellidonombredni8(Integer grupofamiliarapellidonombredni8) {
        this.grupofamiliarapellidonombredni8 = grupofamiliarapellidonombredni8;
        return this;
    }

    public void setGrupofamiliarapellidonombredni8(Integer grupofamiliarapellidonombredni8) {
        this.grupofamiliarapellidonombredni8 = grupofamiliarapellidonombredni8;
    }

    public Integer getGrupofamiliarapellidonombredni9() {
        return grupofamiliarapellidonombredni9;
    }

    public Persona grupofamiliarapellidonombredni9(Integer grupofamiliarapellidonombredni9) {
        this.grupofamiliarapellidonombredni9 = grupofamiliarapellidonombredni9;
        return this;
    }

    public void setGrupofamiliarapellidonombredni9(Integer grupofamiliarapellidonombredni9) {
        this.grupofamiliarapellidonombredni9 = grupofamiliarapellidonombredni9;
    }

    public Integer getGrupofamiliarapellidonombredni10() {
        return grupofamiliarapellidonombredni10;
    }

    public Persona grupofamiliarapellidonombredni10(Integer grupofamiliarapellidonombredni10) {
        this.grupofamiliarapellidonombredni10 = grupofamiliarapellidonombredni10;
        return this;
    }

    public void setGrupofamiliarapellidonombredni10(Integer grupofamiliarapellidonombredni10) {
        this.grupofamiliarapellidonombredni10 = grupofamiliarapellidonombredni10;
    }

    public Integer getGrupofamiliarapellidonombredni11() {
        return grupofamiliarapellidonombredni11;
    }

    public Persona grupofamiliarapellidonombredni11(Integer grupofamiliarapellidonombredni11) {
        this.grupofamiliarapellidonombredni11 = grupofamiliarapellidonombredni11;
        return this;
    }

    public void setGrupofamiliarapellidonombredni11(Integer grupofamiliarapellidonombredni11) {
        this.grupofamiliarapellidonombredni11 = grupofamiliarapellidonombredni11;
    }

    public String getGrupofamiliarapellidonombrefamiliar() {
        return grupofamiliarapellidonombrefamiliar;
    }

    public Persona grupofamiliarapellidonombrefamiliar(String grupofamiliarapellidonombrefamiliar) {
        this.grupofamiliarapellidonombrefamiliar = grupofamiliarapellidonombrefamiliar;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar(String grupofamiliarapellidonombrefamiliar) {
        this.grupofamiliarapellidonombrefamiliar = grupofamiliarapellidonombrefamiliar;
    }

    public String getGrupofamiliarapellidonombrefamiliar2() {
        return grupofamiliarapellidonombrefamiliar2;
    }

    public Persona grupofamiliarapellidonombrefamiliar2(String grupofamiliarapellidonombrefamiliar2) {
        this.grupofamiliarapellidonombrefamiliar2 = grupofamiliarapellidonombrefamiliar2;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar2(String grupofamiliarapellidonombrefamiliar2) {
        this.grupofamiliarapellidonombrefamiliar2 = grupofamiliarapellidonombrefamiliar2;
    }

    public String getGrupofamiliarapellidonombrefamiliar4() {
        return grupofamiliarapellidonombrefamiliar4;
    }

    public Persona grupofamiliarapellidonombrefamiliar4(String grupofamiliarapellidonombrefamiliar4) {
        this.grupofamiliarapellidonombrefamiliar4 = grupofamiliarapellidonombrefamiliar4;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar4(String grupofamiliarapellidonombrefamiliar4) {
        this.grupofamiliarapellidonombrefamiliar4 = grupofamiliarapellidonombrefamiliar4;
    }

    public String getGrupofamiliarapellidonombrefamiliar3() {
        return grupofamiliarapellidonombrefamiliar3;
    }

    public Persona grupofamiliarapellidonombrefamiliar3(String grupofamiliarapellidonombrefamiliar3) {
        this.grupofamiliarapellidonombrefamiliar3 = grupofamiliarapellidonombrefamiliar3;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar3(String grupofamiliarapellidonombrefamiliar3) {
        this.grupofamiliarapellidonombrefamiliar3 = grupofamiliarapellidonombrefamiliar3;
    }

    public String getGrupofamiliarapellidonombrefamiliar5() {
        return grupofamiliarapellidonombrefamiliar5;
    }

    public Persona grupofamiliarapellidonombrefamiliar5(String grupofamiliarapellidonombrefamiliar5) {
        this.grupofamiliarapellidonombrefamiliar5 = grupofamiliarapellidonombrefamiliar5;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar5(String grupofamiliarapellidonombrefamiliar5) {
        this.grupofamiliarapellidonombrefamiliar5 = grupofamiliarapellidonombrefamiliar5;
    }

    public String getGrupofamiliarapellidonombrefamiliar6() {
        return grupofamiliarapellidonombrefamiliar6;
    }

    public Persona grupofamiliarapellidonombrefamiliar6(String grupofamiliarapellidonombrefamiliar6) {
        this.grupofamiliarapellidonombrefamiliar6 = grupofamiliarapellidonombrefamiliar6;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar6(String grupofamiliarapellidonombrefamiliar6) {
        this.grupofamiliarapellidonombrefamiliar6 = grupofamiliarapellidonombrefamiliar6;
    }

    public String getGrupofamiliarapellidonombrefamiliar7() {
        return grupofamiliarapellidonombrefamiliar7;
    }

    public Persona grupofamiliarapellidonombrefamiliar7(String grupofamiliarapellidonombrefamiliar7) {
        this.grupofamiliarapellidonombrefamiliar7 = grupofamiliarapellidonombrefamiliar7;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar7(String grupofamiliarapellidonombrefamiliar7) {
        this.grupofamiliarapellidonombrefamiliar7 = grupofamiliarapellidonombrefamiliar7;
    }

    public String getGrupofamiliarapellidonombrefamiliar8() {
        return grupofamiliarapellidonombrefamiliar8;
    }

    public Persona grupofamiliarapellidonombrefamiliar8(String grupofamiliarapellidonombrefamiliar8) {
        this.grupofamiliarapellidonombrefamiliar8 = grupofamiliarapellidonombrefamiliar8;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar8(String grupofamiliarapellidonombrefamiliar8) {
        this.grupofamiliarapellidonombrefamiliar8 = grupofamiliarapellidonombrefamiliar8;
    }

    public String getGrupofamiliarapellidonombrefamiliar9() {
        return grupofamiliarapellidonombrefamiliar9;
    }

    public Persona grupofamiliarapellidonombrefamiliar9(String grupofamiliarapellidonombrefamiliar9) {
        this.grupofamiliarapellidonombrefamiliar9 = grupofamiliarapellidonombrefamiliar9;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar9(String grupofamiliarapellidonombrefamiliar9) {
        this.grupofamiliarapellidonombrefamiliar9 = grupofamiliarapellidonombrefamiliar9;
    }

    public String getGrupofamiliarapellidonombrefamiliar10() {
        return grupofamiliarapellidonombrefamiliar10;
    }

    public Persona grupofamiliarapellidonombrefamiliar10(String grupofamiliarapellidonombrefamiliar10) {
        this.grupofamiliarapellidonombrefamiliar10 = grupofamiliarapellidonombrefamiliar10;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar10(String grupofamiliarapellidonombrefamiliar10) {
        this.grupofamiliarapellidonombrefamiliar10 = grupofamiliarapellidonombrefamiliar10;
    }

    public String getGrupofamiliarapellidonombrefamiliar11() {
        return grupofamiliarapellidonombrefamiliar11;
    }

    public Persona grupofamiliarapellidonombrefamiliar11(String grupofamiliarapellidonombrefamiliar11) {
        this.grupofamiliarapellidonombrefamiliar11 = grupofamiliarapellidonombrefamiliar11;
        return this;
    }

    public void setGrupofamiliarapellidonombrefamiliar11(String grupofamiliarapellidonombrefamiliar11) {
        this.grupofamiliarapellidonombrefamiliar11 = grupofamiliarapellidonombrefamiliar11;
    }

    public Set<Licencia> getLicencias() {
        return licencias;
    }

    public Persona licencias(Set<Licencia> licencias) {
        this.licencias = licencias;
        return this;
    }

    public Persona addLicencia(Licencia licencia) {
        this.licencias.add(licencia);
        licencia.setPersona(this);
        return this;
    }

    public Persona removeLicencia(Licencia licencia) {
        this.licencias.remove(licencia);
        licencia.setPersona(null);
        return this;
    }

    public void setLicencias(Set<Licencia> licencias) {
        this.licencias = licencias;
    }

    public Set<AltasAscensosBajas> getAltasAscensosBajas() {
        return altasAscensosBajas;
    }

    public Persona altasAscensosBajas(Set<AltasAscensosBajas> altasAscensosBajas) {
        this.altasAscensosBajas = altasAscensosBajas;
        return this;
    }

    public Persona addAltasAscensosBajas(AltasAscensosBajas altasAscensosBajas) {
        this.altasAscensosBajas.add(altasAscensosBajas);
        altasAscensosBajas.setPersona(this);
        return this;
    }

    public Persona removeAltasAscensosBajas(AltasAscensosBajas altasAscensosBajas) {
        this.altasAscensosBajas.remove(altasAscensosBajas);
        altasAscensosBajas.setPersona(null);
        return this;
    }

    public void setAltasAscensosBajas(Set<AltasAscensosBajas> altasAscensosBajas) {
        this.altasAscensosBajas = altasAscensosBajas;
    }

    public Set<Concpremios> getConcpremios() {
        return concpremios;
    }

    public Persona concpremios(Set<Concpremios> concpremios) {
        this.concpremios = concpremios;
        return this;
    }

    public Persona addConcpremios(Concpremios concpremios) {
        this.concpremios.add(concpremios);
        concpremios.setPersona(this);
        return this;
    }

    public Persona removeConcpremios(Concpremios concpremios) {
        this.concpremios.remove(concpremios);
        concpremios.setPersona(null);
        return this;
    }

    public void setConcpremios(Set<Concpremios> concpremios) {
        this.concpremios = concpremios;
    }

    public Set<Embargos> getEmbargos() {
        return embargos;
    }

    public Persona embargos(Set<Embargos> embargos) {
        this.embargos = embargos;
        return this;
    }

    public Persona addEmbargos(Embargos embargos) {
        this.embargos.add(embargos);
        embargos.setPersona(this);
        return this;
    }

    public Persona removeEmbargos(Embargos embargos) {
        this.embargos.remove(embargos);
        embargos.setPersona(null);
        return this;
    }

    public void setEmbargos(Set<Embargos> embargos) {
        this.embargos = embargos;
    }

    public Set<Garantia> getGarantias() {
        return garantias;
    }

    public Persona garantias(Set<Garantia> garantias) {
        this.garantias = garantias;
        return this;
    }

    public Persona addGarantia(Garantia garantia) {
        this.garantias.add(garantia);
        garantia.setPersona(this);
        return this;
    }

    public Persona removeGarantia(Garantia garantia) {
        this.garantias.remove(garantia);
        garantia.setPersona(null);
        return this;
    }

    public void setGarantias(Set<Garantia> garantias) {
        this.garantias = garantias;
    }

    public Set<OtrosServiciosPrestados> getOtrosServiciosPrestados() {
        return otrosServiciosPrestados;
    }

    public Persona otrosServiciosPrestados(Set<OtrosServiciosPrestados> otrosServiciosPrestados) {
        this.otrosServiciosPrestados = otrosServiciosPrestados;
        return this;
    }

    public Persona addOtrosServiciosPrestados(OtrosServiciosPrestados otrosServiciosPrestados) {
        this.otrosServiciosPrestados.add(otrosServiciosPrestados);
        otrosServiciosPrestados.setPersona(this);
        return this;
    }

    public Persona removeOtrosServiciosPrestados(OtrosServiciosPrestados otrosServiciosPrestados) {
        this.otrosServiciosPrestados.remove(otrosServiciosPrestados);
        otrosServiciosPrestados.setPersona(null);
        return this;
    }

    public void setOtrosServiciosPrestados(Set<OtrosServiciosPrestados> otrosServiciosPrestados) {
        this.otrosServiciosPrestados = otrosServiciosPrestados;
    }

    public Set<PenasDisciplinariasSufridas> getPenasDisciplinariasSufridas() {
        return penasDisciplinariasSufridas;
    }

    public Persona penasDisciplinariasSufridas(Set<PenasDisciplinariasSufridas> penasDisciplinariasSufridas) {
        this.penasDisciplinariasSufridas = penasDisciplinariasSufridas;
        return this;
    }

    public Persona addPenasDisciplinariasSufridas(PenasDisciplinariasSufridas penasDisciplinariasSufridas) {
        this.penasDisciplinariasSufridas.add(penasDisciplinariasSufridas);
        penasDisciplinariasSufridas.setPersona(this);
        return this;
    }

    public Persona removePenasDisciplinariasSufridas(PenasDisciplinariasSufridas penasDisciplinariasSufridas) {
        this.penasDisciplinariasSufridas.remove(penasDisciplinariasSufridas);
        penasDisciplinariasSufridas.setPersona(null);
        return this;
    }

    public void setPenasDisciplinariasSufridas(Set<PenasDisciplinariasSufridas> penasDisciplinariasSufridas) {
        this.penasDisciplinariasSufridas = penasDisciplinariasSufridas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Persona)) {
            return false;
        }
        return id != null && id.equals(((Persona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Persona{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", cuil='" + getCuil() + "'" +
            ", dni=" + getDni() +
            ", legajo=" + getLegajo() +
            ", apodo='" + getApodo() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", soltero='" + getSoltero() + "'" +
            ", casado='" + getCasado() + "'" +
            ", conviviente='" + getConviviente() + "'" +
            ", viudo='" + getViudo() + "'" +
            ", domicilio='" + getDomicilio() + "'" +
            ", lugar='" + getLugar() + "'" +
            ", calle='" + getCalle() + "'" +
            ", numero='" + getNumero() + "'" +
            ", telefonofijo='" + getTelefonofijo() + "'" +
            ", numerodecelular='" + getNumerodecelular() + "'" +
            ", oficioprofecion='" + getOficioprofecion() + "'" +
            ", niveldeestudios='" + getNiveldeestudios() + "'" +
            ", gruposanguineo='" + getGruposanguineo() + "'" +
            ", factor='" + getFactor() + "'" +
            ", donante='" + getDonante() + "'" +
            ", diabetes='" + getDiabetes() + "'" +
            ", hipertension='" + getHipertension() + "'" +
            ", alergias='" + getAlergias() + "'" +
            ", asma='" + getAsma() + "'" +
            ", otros='" + getOtros() + "'" +
            ", fechadeingreso='" + getFechadeingreso() + "'" +
            ", instrumentolegal='" + getInstrumentolegal() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", item='" + getItem() + "'" +
            ", planta='" + getPlanta() + "'" +
            ", area='" + getArea() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", annos=" + getAnnos() +
            ", meses=" + getMeses() +
            ", dias=" + getDias() +
            ", realizocomputodeservicios='" + getRealizocomputodeservicios() + "'" +
            ", poseeconocimientoenmaquinasviales='" + getPoseeconocimientoenmaquinasviales() + "'" +
            ", casoemergenciacelular='" + getCasoemergenciacelular() + "'" +
            ", casoemergenciafijo='" + getCasoemergenciafijo() + "'" +
            ", casoemergencianombre='" + getCasoemergencianombre() + "'" +
            ", casoemergenciacelular2='" + getCasoemergenciacelular2() + "'" +
            ", casoemergenciafijo2='" + getCasoemergenciafijo2() + "'" +
            ", casoemergencianombre2='" + getCasoemergencianombre2() + "'" +
            ", familiaracargonombre='" + getFamiliaracargonombre() + "'" +
            ", familiaracargonombre2='" + getFamiliaracargonombre2() + "'" +
            ", familiaracargonombre3='" + getFamiliaracargonombre3() + "'" +
            ", familiaracargonombre4='" + getFamiliaracargonombre4() + "'" +
            ", familiaracargonombre5='" + getFamiliaracargonombre5() + "'" +
            ", familiaracargodni='" + getFamiliaracargodni() + "'" +
            ", familiaracargodni2='" + getFamiliaracargodni2() + "'" +
            ", familiaracargodni3='" + getFamiliaracargodni3() + "'" +
            ", familiaracargodni4='" + getFamiliaracargodni4() + "'" +
            ", familiaracargodni5='" + getFamiliaracargodni5() + "'" +
            ", familiaracargoedad='" + getFamiliaracargoedad() + "'" +
            ", familiaracargoedad2='" + getFamiliaracargoedad2() + "'" +
            ", familiaracargoedad3='" + getFamiliaracargoedad3() + "'" +
            ", familiaracargoedad4='" + getFamiliaracargoedad4() + "'" +
            ", familiaracargoedad5='" + getFamiliaracargoedad5() + "'" +
            ", altura='" + getAltura() + "'" +
            ", barrio='" + getBarrio() + "'" +
            ", estudiosincompletos='" + getEstudiosincompletos() + "'" +
            ", conyugeapellido='" + getConyugeapellido() + "'" +
            ", conyugenombre='" + getConyugenombre() + "'" +
            ", conyugedni=" + getConyugedni() +
            ", conyugecuil='" + getConyugecuil() + "'" +
            ", grupofamiliarapellidonombre='" + getGrupofamiliarapellidonombre() + "'" +
            ", grupofamiliarapellidonombre2='" + getGrupofamiliarapellidonombre2() + "'" +
            ", grupofamiliarapellidonombre3='" + getGrupofamiliarapellidonombre3() + "'" +
            ", grupofamiliarapellidonombre4='" + getGrupofamiliarapellidonombre4() + "'" +
            ", grupofamiliarapellidonombre5='" + getGrupofamiliarapellidonombre5() + "'" +
            ", grupofamiliarapellidonombre6='" + getGrupofamiliarapellidonombre6() + "'" +
            ", grupofamiliarapellidonombre7='" + getGrupofamiliarapellidonombre7() + "'" +
            ", grupofamiliarapellidonombre8='" + getGrupofamiliarapellidonombre8() + "'" +
            ", grupofamiliarapellidonombre9='" + getGrupofamiliarapellidonombre9() + "'" +
            ", grupofamiliarapellidonombre10='" + getGrupofamiliarapellidonombre10() + "'" +
            ", grupofamiliarapellidonombre11='" + getGrupofamiliarapellidonombre11() + "'" +
            ", grupofamiliarapellidonombreedad='" + getGrupofamiliarapellidonombreedad() + "'" +
            ", grupofamiliarapellidonombreedad2='" + getGrupofamiliarapellidonombreedad2() + "'" +
            ", grupofamiliarapellidonombreedad3='" + getGrupofamiliarapellidonombreedad3() + "'" +
            ", grupofamiliarapellidonombreedad4='" + getGrupofamiliarapellidonombreedad4() + "'" +
            ", grupofamiliarapellidonombreedad5='" + getGrupofamiliarapellidonombreedad5() + "'" +
            ", grupofamiliarapellidonombreedad6='" + getGrupofamiliarapellidonombreedad6() + "'" +
            ", grupofamiliarapellidonombreedad7='" + getGrupofamiliarapellidonombreedad7() + "'" +
            ", grupofamiliarapellidonombreedad8='" + getGrupofamiliarapellidonombreedad8() + "'" +
            ", grupofamiliarapellidonombreedad9='" + getGrupofamiliarapellidonombreedad9() + "'" +
            ", grupofamiliarapellidonombreedad10='" + getGrupofamiliarapellidonombreedad10() + "'" +
            ", grupofamiliarapellidonombreedad11='" + getGrupofamiliarapellidonombreedad11() + "'" +
            ", grupofamiliarapellidonombredni=" + getGrupofamiliarapellidonombredni() +
            ", grupofamiliarapellidonombredni2=" + getGrupofamiliarapellidonombredni2() +
            ", grupofamiliarapellidonombredni3=" + getGrupofamiliarapellidonombredni3() +
            ", grupofamiliarapellidonombredni4=" + getGrupofamiliarapellidonombredni4() +
            ", grupofamiliarapellidonombredni5=" + getGrupofamiliarapellidonombredni5() +
            ", grupofamiliarapellidonombredni6=" + getGrupofamiliarapellidonombredni6() +
            ", grupofamiliarapellidonombredni7=" + getGrupofamiliarapellidonombredni7() +
            ", grupofamiliarapellidonombredni8=" + getGrupofamiliarapellidonombredni8() +
            ", grupofamiliarapellidonombredni9=" + getGrupofamiliarapellidonombredni9() +
            ", grupofamiliarapellidonombredni10=" + getGrupofamiliarapellidonombredni10() +
            ", grupofamiliarapellidonombredni11=" + getGrupofamiliarapellidonombredni11() +
            ", grupofamiliarapellidonombrefamiliar='" + getGrupofamiliarapellidonombrefamiliar() + "'" +
            ", grupofamiliarapellidonombrefamiliar2='" + getGrupofamiliarapellidonombrefamiliar2() + "'" +
            ", grupofamiliarapellidonombrefamiliar4='" + getGrupofamiliarapellidonombrefamiliar4() + "'" +
            ", grupofamiliarapellidonombrefamiliar3='" + getGrupofamiliarapellidonombrefamiliar3() + "'" +
            ", grupofamiliarapellidonombrefamiliar5='" + getGrupofamiliarapellidonombrefamiliar5() + "'" +
            ", grupofamiliarapellidonombrefamiliar6='" + getGrupofamiliarapellidonombrefamiliar6() + "'" +
            ", grupofamiliarapellidonombrefamiliar7='" + getGrupofamiliarapellidonombrefamiliar7() + "'" +
            ", grupofamiliarapellidonombrefamiliar8='" + getGrupofamiliarapellidonombrefamiliar8() + "'" +
            ", grupofamiliarapellidonombrefamiliar9='" + getGrupofamiliarapellidonombrefamiliar9() + "'" +
            ", grupofamiliarapellidonombrefamiliar10='" + getGrupofamiliarapellidonombrefamiliar10() + "'" +
            ", grupofamiliarapellidonombrefamiliar11='" + getGrupofamiliarapellidonombrefamiliar11() + "'" +
            "}";
    }
}

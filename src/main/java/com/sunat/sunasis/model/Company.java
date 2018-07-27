package com.sunat.sunasis.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")
@NamedQueries(value = {
        @NamedQuery(name = "getCompaniesByRucOrRazonSocial", query = "select c from Company c where lower(c.razonSocial) like :razonSocial or c.ruc=:ruc")
})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated company ID")
    private long id;

    @NotNull
    @Column(name = "ruc")
    private String ruc;

    @NotNull
    @Column(name = "razon_social")
    private String razonSocial;

    @NotNull
    @Column(name = "estado_contribuyente")
    private String estado;

    @NotNull
    @Column(name = "condicion_domicilio")
    private String condicion;

    @NotNull
    @Column(name = "ubigeo")
    private String ubigeo;

    @NotNull
    @Column(name = "tipo_via")
    private String tipoVia;

    @NotNull
    @Column(name = "nombre_via")
    private String nombreVia;

    @NotNull
    @Column(name = "codigo_zona")
    private String codigoZona;

    @NotNull
    @Column(name = "tipo_zona")
    private String tipoZona;

    @NotNull
    @Column(name = "numero")
    private String numero;

    @NotNull
    @Column(name = "interior")
    private String interior;
    @NotNull
    @Column(name = "lote")
    private String lote;

    @NotNull
    @Column(name = "departamento")
    private String departamento;

    @NotNull
    @Column(name = "manzana")
    private String manzana;

    @NotNull
    @Column(name = "kilometro")
    private String kilometro;

/*    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version", foreignKey = @ForeignKey)
    private Version version;*/

    public Company() {
    }

    public Company(@NotNull String ruc, @NotNull String razonSocial, @NotNull String estado, @NotNull String condicion, @NotNull String ubigeo, @NotNull String tipoVia, @NotNull String nombreVia, @NotNull String codigoZona, @NotNull String tipoZona, @NotNull String numero, @NotNull String interior, @NotNull String lote, @NotNull String departamento, @NotNull String manzana, @NotNull String kilometro) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.estado = estado;
        this.condicion = condicion;
        this.ubigeo = ubigeo;
        this.tipoVia = tipoVia;
        this.nombreVia = nombreVia;
        this.codigoZona = codigoZona;
        this.tipoZona = tipoZona;
        this.numero = numero;
        this.interior = interior;
        this.lote = lote;
        this.departamento = departamento;
        this.manzana = manzana;
        this.kilometro = kilometro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getRuc() {
        return ruc;
    }

    public void setRuc(@NotNull String ruc) {
        this.ruc = ruc;
    }

    @NotNull
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(@NotNull String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @NotNull
    public String getEstado() {
        return estado;
    }

    public void setEstado(@NotNull String estado) {
        this.estado = estado;
    }

    @NotNull
    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(@NotNull String condicion) {
        this.condicion = condicion;
    }

    @NotNull
    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(@NotNull String ubigeo) {
        this.ubigeo = ubigeo;
    }

    @NotNull
    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(@NotNull String tipoVia) {
        this.tipoVia = tipoVia;
    }

    @NotNull
    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(@NotNull String nombreVia) {
        this.nombreVia = nombreVia;
    }

    @NotNull
    public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(@NotNull String codigoZona) {
        this.codigoZona = codigoZona;
    }

    @NotNull
    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(@NotNull String tipoZona) {
        this.tipoZona = tipoZona;
    }

    @NotNull
    public String getNumero() {
        return numero;
    }

    public void setNumero(@NotNull String numero) {
        this.numero = numero;
    }

    @NotNull
    public String getInterior() {
        return interior;
    }

    public void setInterior(@NotNull String interior) {
        this.interior = interior;
    }

    @NotNull
    public String getLote() {
        return lote;
    }

    public void setLote(@NotNull String lote) {
        this.lote = lote;
    }

    @NotNull
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(@NotNull String departamento) {
        this.departamento = departamento;
    }

    @NotNull
    public String getManzana() {
        return manzana;
    }

    public void setManzana(@NotNull String manzana) {
        this.manzana = manzana;
    }

    @NotNull
    public String getKilometro() {
        return kilometro;
    }

    public void setKilometro(@NotNull String kilometro) {
        this.kilometro = kilometro;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", ruc='" + ruc + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", estado='" + estado + '\'' +
                ", condicion='" + condicion + '\'' +
                ", ubigeo='" + ubigeo + '\'' +
                ", tipoVia='" + tipoVia + '\'' +
                ", nombreVia='" + nombreVia + '\'' +
                ", codigoZona='" + codigoZona + '\'' +
                ", tipoZona='" + tipoZona + '\'' +
                ", numero='" + numero + '\'' +
                ", interior='" + interior + '\'' +
                ", lote='" + lote + '\'' +
                ", departamento='" + departamento + '\'' +
                ", manzana='" + manzana + '\'' +
                ", kilometro='" + kilometro + '\'' +
                '}';
    }
}

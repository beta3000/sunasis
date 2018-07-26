package com.sunat.sunasis.model;


import javax.persistence.*;

@Entity
@Table(name = "COMPANY")
public class CompanyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ruc;
    private String razonSocial;
    private String estado;
    private String condicion;
    private String ubigeo;
    private String tipoVia;
    private String nombreVia;
    private String codigoZona;
    private String tipoZona;
    private String numero;
    private String interior;
    private String lote;
    private String departamento;
    private String manzana;
    private String kilometro;

    public CompanyModel() {
    }

    public CompanyModel(String ruc, String razonSocial, String estado, String condicion, String ubigeo, String tipoVia, String nombreVia, String codigoZona, String tipoZona, String numero, String interior, String lote, String departamento, String manzana, String kilometro) {
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getKilometro() {
        return kilometro;
    }

    public void setKilometro(String kilometro) {
        this.kilometro = kilometro;
    }

    @Override
    public String toString() {
        return "CompanyModel{" +
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

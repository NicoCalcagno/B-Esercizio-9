package com.example.demobanca.model;


import java.util.Date;

public class MovimentiDTO {
    private Long idmovimento;

    private Date datamovimento;


    private String tipo;//prelievo o deposito


    private Float cifra;


    private Float saldo;


    private Conto conto;


    public MovimentiDTO() {
        //do nothing
    }

    public Long getIdmovimento() {
        return idmovimento;
    }

    public void setIdmovimento(Long idmovimento) {
        this.idmovimento = idmovimento;
    }

    public Date getDatamovimento() {
        return datamovimento;
    }

    public void setDatamovimento(Date datamovimento) {
        this.datamovimento = datamovimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getCifra() {
        return cifra;
    }

    public void setCifra(Float cifra) {
        this.cifra = cifra;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Conto getConto() {
        return conto;
    }

    public void setConto(Conto conto) {
        this.conto = conto;
    }
}

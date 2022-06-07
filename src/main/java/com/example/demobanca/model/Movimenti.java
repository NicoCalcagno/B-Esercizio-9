package com.example.demobanca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movimenti")
public class Movimenti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_movimento")
    private Long idmovimento;

    @Column(name = "data")
    private Date datamovimento;

    @Column(name = "tipo")
    private String tipo;//prelievo o deposito

    @Column(name = "cifra")
    private Float cifra;

    @Column(name = "saldo")
    private Float saldo;

    @ManyToOne
    @JoinColumn(name="id_conto")
    private Conto conto;


    public Movimenti() {
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

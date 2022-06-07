package com.example.demobanca.model;

import javax.persistence.*;


@Entity
@Table(name = "conto")
public class Conto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_conto")
    private Long idconto;

    @Column(name = "saldo")
    private Float saldo;

    @OneToOne
    @JoinColumn(name = "client")
    private Client client;






    public Conto() {
        //do nothing
    }

    public Long getIdconto() {
        return idconto;
    }

    public void setIdconto(Long idconto) {
        this.idconto = idconto;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}

package com.example.demobanca.model;



public class ContoDTO {

    private Long idconto;


    private Float saldo;


    private Client client;






    public ContoDTO() {
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

package com.example.demobanca.dto;


import com.example.demobanca.entity.Account;

import java.util.Date;

public class MovementDTO {
    private Long movementId;

    private Date dataMovement;


    private String type;//prelievo o deposito


    private Float amount;


    private Float balance;


    private Account account;


    public MovementDTO() {
        //do nothing
    }

    public Long getMovementId() {
        return movementId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public Date getDataMovement() {
        return dataMovement;
    }

    public void setDataMovement(Date dataMovement) {
        this.dataMovement = dataMovement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

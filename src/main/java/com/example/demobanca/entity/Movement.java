package com.example.demobanca.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movement")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movement_id")
    private Long movementId;

    @Column(name = "data_movement")
    private Date dataMovement;

    @Column(name = "type")
    private String type;//prelievo o deposito

    @Column(name = "amount")
    private Float amount;

    @Column(name = "balance")
    private Float balance;

    @ManyToOne
    @JoinColumn(name="account")
    private Account account;


    public Movement() {
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

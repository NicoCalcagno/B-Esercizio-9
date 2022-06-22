package com.example.demobanca.dto;


import com.example.demobanca.entity.Client;

public class AccountDTO {

    private Long accountId;


    private Float balance;


    private Client client;






    public AccountDTO() {
        //do nothing
    }

    public java.lang.Long getAccountId() {
        return accountId;
    }

    public void setAccountId(java.lang.Long accountId) {
        this.accountId = accountId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}

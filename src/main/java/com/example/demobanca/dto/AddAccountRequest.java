package com.example.demobanca.dto;


public class AddAccountRequest {



    private Long accountId;


    private Float balance;


    private Long clientId;






    public AddAccountRequest() {
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

    public Long getClientId() {
        return clientId;
    }

    public void setClient(Long clientId) {
        this.clientId = clientId;
    }


}

package com.example.demobanca.dao;

import com.example.demobanca.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;


public interface AccountRepositoryAccessDb extends JpaRepository<Account, Long> {
    @Modifying
    @Query("DELETE FROM Account a WHERE a.accountId = :accountId")
    int deleteAccountByAccountId(Long accountId);

    @Modifying
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.accountId = :accountId")
    int updateBalanceByAccountId(Long accountId, float balance);
    Optional<Account> findAccountByAccountId(Long accountId);
}

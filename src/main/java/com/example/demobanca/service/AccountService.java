package com.example.demobanca.service;

import com.example.demobanca.dao.AccountRepositoryAccessDb;
import com.example.demobanca.dao.ClientRepositoryAccessDb;
import com.example.demobanca.dto.AddAccountRequest;
import com.example.demobanca.entity.Account;
import com.example.demobanca.entity.Client;
import com.example.demobanca.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final AccountRepositoryAccessDb accountRepository;
    private final ClientRepositoryAccessDb clientRepositoryAccessDb;

    @Autowired
    public AccountService(AccountRepositoryAccessDb accountRepository, ClientRepositoryAccessDb clientRepositoryAccessDb) {
        this.accountRepository = accountRepository;
        this.clientRepositoryAccessDb = clientRepositoryAccessDb;
    }

    public Account addAccount(AddAccountRequest addAccountRequest){
        Account accountToSave = new Account();
        Optional<Client> clientToAddAccount = clientRepositoryAccessDb.findClientByClientId(addAccountRequest.getClientId());
        clientToAddAccount.ifPresent(accountToSave::setClient);
        accountToSave.setBalance(addAccountRequest.getBalance());
        return accountRepository.save(accountToSave);
    }

    public List<Account> retrieveAllAccount(){
        List<Account> allAccount = accountRepository.findAll();
        if(allAccount.isEmpty()){
            throw new NotFoundException("accounts not found");
        }

        return allAccount;
    }

    public Account updateBalance(Account account){

        if(accountRepository.updateBalanceByAccountId(account.getAccountId(), account.getBalance()) == 0){
            throw new NotFoundException("account not found");
        }


        return accountRepository.findAccountByAccountId(account.getAccountId())
                .map(acc -> account)
                .orElseThrow(() ->{
                        throw new NotFoundException("account not found");}
                );
    }

    public void deleteAccount(Long accountId){
        int resultOfDelete = accountRepository.deleteAccountByAccountId(accountId);
        if(resultOfDelete == 0){
            throw new NotFoundException("account not found");
        }
    }

    public Optional<Account> findById(Long id){
        return accountRepository.findById(id);
    }
}

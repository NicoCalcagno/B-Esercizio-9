package com.example.demobanca.api;

import com.example.demobanca.dto.AddAccountRequest;
import com.example.demobanca.entity.Account;

import com.example.demobanca.exception.NotFoundException;
import com.example.demobanca.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody AddAccountRequest addAccountRequest){
        return new ResponseEntity<>(accountService.addAccount(addAccountRequest),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> retrieveAllAccounts(){
        try{
            List<Account> allAccount = accountService.retrieveAllAccount();
            return new ResponseEntity<>(allAccount, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public Optional<Account> retrieveAccountByAccountId(@RequestParam Long id){
        return accountService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {

        try{
            accountService.deleteAccount(id);
            return new ResponseEntity<>("account removed",HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}

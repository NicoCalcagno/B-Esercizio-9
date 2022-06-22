package com.example.demobanca.service;

import com.example.demobanca.dao.AccountRepositoryAccessDb;
import com.example.demobanca.dao.MovementRepositoryAccessDb;

import com.example.demobanca.dto.AddMovementRequest;
import com.example.demobanca.dto.MovementDTO;
import com.example.demobanca.entity.Account;

import com.example.demobanca.entity.Movement;
import com.example.demobanca.exception.NotFoundException;
import com.example.demobanca.exception.WithDrawalException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MovementService {

    private final MovementRepositoryAccessDb movementRepository;
    private final AccountRepositoryAccessDb accountRepositoryAccessDb;

    @Autowired
    public MovementService(MovementRepositoryAccessDb movementRepository, AccountRepositoryAccessDb accountRepositoryAccessDb) {
        this.movementRepository = movementRepository;

        this.accountRepositoryAccessDb = accountRepositoryAccessDb;
    }

    public Account addMovement(AddMovementRequest addMovementRequest){
        MovementDTO movementDTO = new MovementDTO();
        Optional<Account> accountToAddMovement = accountRepositoryAccessDb.findAccountByAccountId(addMovementRequest.getAccountId());
        accountToAddMovement.ifPresent(movementDTO::setAccount);

        float balanceAccount = movementDTO.getAccount().getBalance();

        //Aggiorniamo saldo del movement
        if (addMovementRequest.getType().equals("Prelievo")) {
            if(balanceAccount < addMovementRequest.getAmount()){
                throw new WithDrawalException("Amount not available");
            }
            addMovementRequest.setBalance(balanceAccount - addMovementRequest.getAmount());
        } else if (addMovementRequest.getType().equals("Deposito")) {
            addMovementRequest.setBalance(balanceAccount + addMovementRequest.getAmount());
        }

        Movement movementToSave =  new Movement();
        movementToSave.setAccount(movementDTO.getAccount());
        movementToSave.setType(addMovementRequest.getType());
        movementToSave.setAmount(addMovementRequest.getAmount());
        movementToSave.setBalance(addMovementRequest.getBalance());
        //Set data today
        movementToSave.setDataMovement(new Date(System.currentTimeMillis()));
        movementToSave.getAccount().setBalance(addMovementRequest.getBalance());
        return movementRepository.save(movementToSave).getAccount();
    }

    public void deleteMovement(Long movementId){
        int resultOfDelete = movementRepository.deleteClientByClientId(movementId);
        if(resultOfDelete == 0){
            throw new NotFoundException("movement not found");
        }

    }

    public List<Movement> retrieveAllMovements(){
        List<Movement> allMovement = movementRepository.findAll();
        if(allMovement.isEmpty()){
            throw new NotFoundException("movements not found");
        }
        return allMovement;
    }

    public List<Movement> retrieveAllMovementsByAccount(Optional<Account> movement){
        return movementRepository.findByAccount(movement);
    }

    public Optional<Movement> findById(Long movementId){
        return movementRepository.findMovementByMovementId(movementId);
    }
}

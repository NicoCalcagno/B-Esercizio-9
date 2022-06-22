package com.example.demobanca.api;

import com.example.demobanca.dto.AddMovementRequest;
import com.example.demobanca.entity.Account;
import com.example.demobanca.entity.Movement;

import com.example.demobanca.exception.NotFoundException;
import com.example.demobanca.exception.WithDrawalException;
import com.example.demobanca.service.AccountService;
import com.example.demobanca.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movement")
public class MovementController {


    private final MovementService movementService;

    private final AccountService accountService;

    @Autowired
    public MovementController(MovementService movementService, AccountService accountService) {
        this.movementService = movementService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> addMovement(@RequestBody AddMovementRequest addMovementRequest){

        try{
            //Update balance of account after add movement
            Account accountUpdated = accountService.updateBalance(movementService.addMovement(addMovementRequest));
            return new ResponseEntity<>(accountUpdated, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (WithDrawalException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Movement>> retrieveAllMovement(){
        try{
            List<Movement> allMovement = movementService.retrieveAllMovements();
            return new ResponseEntity<>(allMovement, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/account/{id}")
    public ResponseEntity<List<Movement>> retrieveMovementByAccount(@PathVariable Long id){
        Optional<Account> account = accountService.findById(id);
        if(!account.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Movement> movementList = movementService.retrieveAllMovementsByAccount(account);

        if(movementList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if(movementList.size() <= 5) {
            return new ResponseEntity<>(movementList, HttpStatus.OK);
        }

        return new ResponseEntity<>(movementList.subList(movementList.size()-5, movementList.size()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovement(@PathVariable("id") Long id) {

        try{
            movementService.deleteMovement(id);
            return new ResponseEntity<>("movement removed",HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}

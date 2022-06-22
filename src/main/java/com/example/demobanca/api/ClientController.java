package com.example.demobanca.api;


import com.example.demobanca.entity.Client;
import com.example.demobanca.dto.ClientDTO;
import com.example.demobanca.exception.NotFoundException;
import com.example.demobanca.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody ClientDTO clientDto){
        return new ResponseEntity<>(clientService.addClient(clientDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Client>> retrieveAllClients(){
        try{
            List<Client> allClient = clientService.retrieveAllClient();
            return new ResponseEntity<>(allClient, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDto){
        try{
            ClientDTO clientUpdated = clientService.saveUpdate(clientDto);
            return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) {

        try{
            clientService.deleteClient(id);
            return new ResponseEntity<>("client removed",HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}

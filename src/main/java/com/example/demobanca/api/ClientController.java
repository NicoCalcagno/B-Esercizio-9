package com.example.demobanca.api;

import com.example.demobanca.model.Client;
import com.example.demobanca.model.ClientDTO;
import com.example.demobanca.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/add")
    public void addClient(@RequestBody ClientDTO client){
        Client clientToSave = new Client();
        clientToSave.setName(client.getName());
        clientToSave.setSurname(client.getSurname());
        clientToSave.setEmail(client.getEmail());
        clientToSave.setTel(client.getTel());
        clientService.addClient(clientToSave);
    }

    @GetMapping(value = "/all")
    public List<Client> getAllClient(){
        return clientService.getAllClient();
    }

}

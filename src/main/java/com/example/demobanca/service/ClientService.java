package com.example.demobanca.service;

import com.example.demobanca.dao.ClientRepositoryAccessDb;
import com.example.demobanca.model.Client;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {


    private final ClientRepositoryAccessDb clientRepository;

    @Autowired
    public ClientService(ClientRepositoryAccessDb clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client){
        return clientRepository.save(client);
    }

    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }
}

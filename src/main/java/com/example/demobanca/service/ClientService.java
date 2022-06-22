package com.example.demobanca.service;

import com.example.demobanca.dao.ClientRepositoryAccessDb;
import com.example.demobanca.dto.ClientDTO;
import com.example.demobanca.entity.Client;
import com.example.demobanca.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ClientService {


    private final ClientRepositoryAccessDb clientRepository;

    @Autowired
    public ClientService(ClientRepositoryAccessDb clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(ClientDTO clientDto){

        Client clientToSave = new Client();
        clientToSave.setName(clientDto.getName());
        clientToSave.setSurname(clientDto.getSurname());
        clientToSave.setEmail(clientDto.getEmail());
        clientToSave.setTel(clientDto.getTel());
        clientToSave.setImageUrl(clientDto.getImageUrl());

        return clientRepository.save(clientToSave);
    }

    public List<Client> retrieveAllClient(){
        List<Client> allClient = clientRepository.findAll();

        if(allClient.isEmpty()){
            throw new NotFoundException("clients not found");
        }

        return clientRepository.findAll();
    }

    public Optional<Client> findClientByClientId(Long clientId) {
        return clientRepository.findClientByClientId(clientId);
    }

    public ClientDTO saveUpdate(ClientDTO clientDto) {
        if(clientRepository.updateClient(clientDto) == 0){
            throw new NotFoundException("client not found");
        }
        return clientRepository.findClientByClientId(clientDto.getClientId())
                .map(acc -> clientDto)
                .orElseThrow(() ->{
                    throw new NotFoundException("client not found");}
                );
    }


    public void deleteClient(Long clientId){
        int resultOfDelete = clientRepository.deleteClientByClientId(clientId);
        if(resultOfDelete == 0){
            throw new NotFoundException("client not found");
        }

    }
}

package com.example.demobanca.service;

import com.example.demobanca.dao.ClientRepositoryAccessDb;
import com.example.demobanca.model.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepositoryAccessDb clientRepositoryAccessDb;

    @Test
    @DisplayName("Test addClient")
    void testAddClient(){
        //Setup mock repository
        Client client = new Client();
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");

        //doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD
        doReturn(client).when(clientRepositoryAccessDb).save(any());

        //Execute the service call
        Client returnedClient = clientService.addClient(client);

        //Assert the response
        Assertions.assertNotNull(returnedClient, "The saved Client should not be null");

    }

    @Test
    @DisplayName("Test getAllClient")
    void testGetAllClient(){
        //Setup mock repository
        Client client = new Client();
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");

        Client client1 = new Client();
        client1.setName("Carla");
        client1.setSurname("Magna");
        client1.setEmail("carlamagna@romo.it");
        client1.setTel("3661422686");

        //doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD
        doReturn(Arrays.asList(client, client1)).when(clientRepositoryAccessDb).findAll();

        //Execute the service call
        List<Client> clientList = clientService.getAllClient();

        //Assert the response
        //Condizione per cui verifica che è andato tutto liscio
        Assertions.assertEquals(2, clientList.size(), "getAllClient should return 2 client");
    }
}

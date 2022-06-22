package com.example.demobanca.service;

import com.example.demobanca.dao.ClientRepositoryAccessDb;
import com.example.demobanca.dto.ClientDTO;
import com.example.demobanca.entity.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepositoryAccessDb clientRepositoryAccessDb;

    @Test
    @DisplayName("Test addClient")
    void testAddClient(){
        //Setup mock repository
        ClientDTO client = new ClientDTO();
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");
        client.setImageUrl("https://google.com");

        //when(MOCK_CLASS_INSTANCE.MOCK_METHOD).then(Return(PARAMETER_TO_INVOKE_MOCK_METHOD))
        when(clientRepositoryAccessDb.save(any())).then(AdditionalAnswers.returnsFirstArg());

        //Execute the service call
        Client returnedClient = clientService.addClient(client);

        //Assert the response

        Assertions.assertEquals(client.getName(), returnedClient.getName(), "name ok");
        Assertions.assertEquals(client.getSurname(), returnedClient.getSurname(), "surname ok");
        Assertions.assertEquals(client.getEmail(), returnedClient.getEmail(), "email ok");
        Assertions.assertEquals(client.getTel(), returnedClient.getTel(), "tel ok");
        Assertions.assertEquals(client.getImageUrl(), returnedClient.getImageUrl(), "imageUrl ok");
    }

    @Test
    @DisplayName("Test retrieveAllClients")
    void testRetrieveAllClient(){
        //Setup mock repository
        Client client = new Client();
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");
        client.setImageUrl("https://google.com");

        Client client1 = new Client();
        client1.setName("Carla");
        client1.setSurname("Magna");
        client1.setEmail("carlamagna@romo.it");
        client1.setTel("3661422686");
        client.setImageUrl("https://google.com");


        //when(MOCK_CLASS_INSTANCE.MOCK_METHOD).thenReturn())
        when(clientRepositoryAccessDb.findAll()).thenReturn(Arrays.asList(client, client1));

        //Execute the service call
        List<Client> clientList = clientService.retrieveAllClient();

        //Assert the response
        //Condizione per cui verifica che è andato tutto liscio
        Assertions.assertEquals(2, clientList.size(), "retrieveAllClients should return 2 client");
    }
}

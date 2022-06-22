package com.example.demobanca.api;

import com.example.demobanca.dto.ClientDTO;
import com.example.demobanca.entity.Client;
import com.example.demobanca.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(("GET /api/client"))
    void testRetrieveAllClient() throws Exception {
        //Setup out mocked service
        Client client =  new Client();
        client.setClientId(1L);
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");
        client.setImageUrl("https://google.com");

        Client client1 = new Client();
        client1.setClientId(2L);
        client1.setName("Carla");
        client1.setSurname("Magna");
        client1.setEmail("carlamagna@romo.it");
        client1.setTel("3121422293");
        client1.setImageUrl("https://google.com");

        //doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD
        doReturn(Lists.newArrayList(client, client1)).when(clientService).retrieveAllClient();

        // Execute the GET request
        mockMvc.perform(get("/api/client"))
                // Validate the content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].clientId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Carlo")))
                .andExpect(jsonPath("$[0].surname", is("Magno")))
                .andExpect(jsonPath("$[0].email", is("carlomagno@roma.it")))
                .andExpect(jsonPath("$[0].tel", is("3791422686")))
                .andExpect(jsonPath("$[1].clientId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Carla")))
                .andExpect(jsonPath("$[1].surname", is("Magna")))
                .andExpect(jsonPath("$[1].email", is("carlamagna@romo.it")))
                .andExpect(jsonPath("$[1].tel", is("3121422293")));

    }

    @Test
    @DisplayName("POST /api/client")
    void testAddClient() throws Exception {
        //Setup our mocked service
        ClientDTO client = new ClientDTO();
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");
        client.setImageUrl("https://google.com");

        Client clientToReturn = new Client();
        clientToReturn.setClientId(1l);
        clientToReturn.setName("Carlo");
        clientToReturn.setSurname("Magno");
        clientToReturn.setEmail("carlomagno@roma.it");
        clientToReturn.setTel("3791422686");
        clientToReturn.setImageUrl("https://google.com");

        //doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD
        doReturn(clientToReturn).when(clientService).addClient(any());

        //Execute the POST request
        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))

                // Validate content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //validate the returned fields
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1))
                .andExpect(jsonPath("$.name").value("Carlo"))
                .andExpect(jsonPath("$.surname").value("Magno"))
                .andExpect(jsonPath("$.email").value("carlomagno@roma.it"))
                .andExpect(jsonPath("$.tel").value("3791422686"))
                .andExpect(jsonPath("$.imageUrl").value("https://google.com"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

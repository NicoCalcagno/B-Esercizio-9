package com.example.demobanca.api;

import com.example.demobanca.model.Client;
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
    @DisplayName(("GET /api/client/all"))
    void testGetAllClient() throws Exception {
        //Setup out mocked service
        Client client =  new Client();
        client.setIdclient(1l);
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");

        Client client1 = new Client();
        client1.setIdclient(2l);
        client1.setName("Carla");
        client1.setSurname("Magna");
        client1.setEmail("carlamagna@romo.it");
        client1.setTel("3121422293");

        //doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD
        doReturn(Lists.newArrayList(client, client1)).when(clientService).getAllClient();

        // Execute the GET request
        mockMvc.perform(get("/api/client/all"))
                // Validate the content type
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idclient", is(1)))
                .andExpect(jsonPath("$[0].name", is("Carlo")))
                .andExpect(jsonPath("$[0].surname", is("Magno")))
                .andExpect(jsonPath("$[0].email", is("carlomagno@roma.it")))
                .andExpect(jsonPath("$[0].tel", is("3791422686")))
                .andExpect(jsonPath("$[1].idclient", is(2)))
                .andExpect(jsonPath("$[1].name", is("Carla")))
                .andExpect(jsonPath("$[1].surname", is("Magna")))
                .andExpect(jsonPath("$[1].email", is("carlamagna@romo.it")))
                .andExpect(jsonPath("$[1].tel", is("3121422293")));

    }

    @Test
    @DisplayName("POST /api/client/add")
    void testAddClient() throws Exception {
        //Setup our mocked service
        Client client = new Client();
        client.setName("Carlo");
        client.setSurname("Magno");
        client.setEmail("carlomagno@roma.it");
        client.setTel("3791422686");

        Client clientToReturn = new Client();
        clientToReturn.setIdclient(1l);
        clientToReturn.setName("Carlo");
        clientToReturn.setSurname("Magno");
        clientToReturn.setEmail("carlomagno@roma.it");
        clientToReturn.setTel("3791422686");

        //doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD
        doReturn(clientToReturn).when(clientService).addClient(any());

        //Execute the POST request
        mockMvc.perform(post("/api/client/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))

                // Validate content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //validate the returned fields
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idclient", is(1)))
                .andExpect(jsonPath("$.name", is("Carlo")))
                .andExpect(jsonPath("$.surname", is("Magno")))
                .andExpect(jsonPath("$.email", is("carlomagno@roma.it")))
                .andExpect(jsonPath("$.tel", is("3791422686")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

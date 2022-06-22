package com.example.demobanca.dao;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryAccessDbTest {


    @Autowired
    private ClientRepositoryAccessDb clientRepository;


    @Test
    void deleteClientByClientId() {
        int resultOfDelete = clientRepository.deleteClientByClientId(1L);
        Assertions.assertEquals(1,resultOfDelete);
    }

}

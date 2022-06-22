package com.example.demobanca.dao;

import com.example.demobanca.dto.ClientDTO;
import com.example.demobanca.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;


public interface ClientRepositoryAccessDb extends JpaRepository<Client, java.lang.Long> {

    @Modifying
    @Query("DELETE FROM Client c WHERE c.clientId = :clientId")
    int deleteClientByClientId(java.lang.Long clientId);

    @Modifying
    @Query(value = "UPDATE Client SET name = :#{#client.name}," +
            "surname = :#{#client.surname}," +
            "email = :#{#client.email}," +
            "imageUrl = :#{#client.imageUrl}," +
            "tel = :#{#client.tel} " +
            "WHERE (clientId = :#{#client.clientId})")
    int updateClient(@Param("client") ClientDTO client);

    Optional<Client> findClientByClientId(Long clientId);
}

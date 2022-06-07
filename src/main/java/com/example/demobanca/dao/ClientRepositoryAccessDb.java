package com.example.demobanca.dao;

import com.example.demobanca.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ClientRepositoryAccessDb extends JpaRepository<Client, Long> {
}

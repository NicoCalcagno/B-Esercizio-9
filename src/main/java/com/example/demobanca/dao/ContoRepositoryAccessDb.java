package com.example.demobanca.dao;

import com.example.demobanca.model.Conto;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContoRepositoryAccessDb extends JpaRepository<Conto, Long> {

}

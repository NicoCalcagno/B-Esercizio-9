package com.example.demobanca.dao;

import com.example.demobanca.entity.Account;
import com.example.demobanca.entity.Movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;


public interface MovementRepositoryAccessDb extends JpaRepository<Movement, Long> {
    @Modifying
    @Query("DELETE FROM Movement m WHERE m.movementId = :movementId")
    int deleteClientByClientId(Long movementId);
    List<Movement> findByAccount(Optional<Account> account);

    Optional<Movement> findMovementByMovementId(Long movementId);
}

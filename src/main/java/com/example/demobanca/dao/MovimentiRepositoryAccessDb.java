package com.example.demobanca.dao;

import com.example.demobanca.model.Conto;
import com.example.demobanca.model.Movimenti;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface MovimentiRepositoryAccessDb extends JpaRepository<Movimenti, Long> {

    //@Query("SELECT m FROM Movimenti m WHERE m.conto.id_conto = :idconto")
    public List<Movimenti> findByConto(Optional<Conto> conto);
}

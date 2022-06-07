package com.example.demobanca.service;

import com.example.demobanca.dao.MovimentiRepositoryAccessDb;

import com.example.demobanca.model.Conto;
import com.example.demobanca.model.Movimenti;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentiService {

    private final MovimentiRepositoryAccessDb movimentiRepository;


    @Autowired
    public MovimentiService(MovimentiRepositoryAccessDb movimentiRepository) {
        this.movimentiRepository = movimentiRepository;

    }

    public void addMovimento(Movimenti movimento){
        movimentiRepository.save(movimento);
    }

    public List<Movimenti> getAllMovimenti(){
        return movimentiRepository.findAll();
    }

    public List<Movimenti> getAllMovimentiByConto(Optional<Conto> conto){
        return movimentiRepository.findByConto(conto);
    }
}

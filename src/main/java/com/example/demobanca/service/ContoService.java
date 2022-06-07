package com.example.demobanca.service;

import com.example.demobanca.dao.ContoRepositoryAccessDb;
import com.example.demobanca.model.Conto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContoService {

    private final ContoRepositoryAccessDb contoRepository;

    @Autowired
    public ContoService(ContoRepositoryAccessDb contoRepository) {
        this.contoRepository = contoRepository;
    }

    public void addConto(Conto conto){
        contoRepository.save(conto);
    }

    public List<Conto> getAllConto(){
        return contoRepository.findAll();
    }

    public void saveUpdate(Optional<Conto> conto){
        if(conto.isPresent()) { contoRepository.save(conto.get()); }
    }

    public Optional<Conto> findById(Long id){
        return contoRepository.findById(id);
    }
}

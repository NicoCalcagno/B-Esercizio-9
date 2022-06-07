package com.example.demobanca.api;

import com.example.demobanca.model.Conto;
import com.example.demobanca.model.ContoDTO;
import com.example.demobanca.service.ContoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conto")
public class ContoController {

    private final ContoService contoService;

    @Autowired
    public ContoController(ContoService contoService) {
        this.contoService = contoService;
    }

    @PostMapping(value = "/add")
    public void addConto(@RequestBody ContoDTO conto){
        Conto contoToSave = new Conto();
        contoToSave.setClient(conto.getClient());
        contoToSave.setSaldo(conto.getSaldo());
        contoService.addConto(contoToSave);
    }

    @GetMapping(value = "/all")
    public List<Conto> getAllConto(){
        return contoService.getAllConto();
    }

    @GetMapping(value = "/{id}")
    public Optional<Conto> getContoById(@PathVariable(value = "id") Long id){
        return contoService.findById(id);
    }


}

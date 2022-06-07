package com.example.demobanca.api;

import com.example.demobanca.model.Conto;
import com.example.demobanca.model.Movimenti;
import com.example.demobanca.model.MovimentiDTO;
import com.example.demobanca.service.ContoService;
import com.example.demobanca.service.MovimentiService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimenti")
public class MovimentiController {


    private final MovimentiService movimentiService;

    private final ContoService contoService;

    @Autowired
    public MovimentiController(MovimentiService movimentiService, ContoService contoService) {
        this.movimentiService = movimentiService;
        this.contoService = contoService;
    }

    @PostMapping(value = "/add")
    public void addMovimento(@RequestBody MovimentiDTO movimento){
        //salviamo il movimento
        Movimenti movimentoToSave =  new Movimenti();
        movimentoToSave.setConto(movimento.getConto());
        movimentoToSave.setTipo(movimento.getTipo());
        movimentoToSave.setCifra(movimento.getCifra());
        movimentoToSave.setDatamovimento(movimento.getDatamovimento());


        //Aggiorniamo saldo e lista movimenti del conto
        Optional<Conto> contoToUpdate = contoService.findById(movimentoToSave.getConto().getIdconto());


        if (movimentoToSave.getTipo().equals("Prelievo")) {
            if(contoToUpdate.isPresent()){
                contoToUpdate.ifPresent(Conto -> Conto.setSaldo(contoToUpdate.get().getSaldo() - movimentoToSave.getCifra()));
            }
        } else if (movimentoToSave.getTipo().equals("Deposito")) {
            contoToUpdate.ifPresent(Conto -> Conto.setSaldo(contoToUpdate.get().getSaldo() + movimentoToSave.getCifra()));
        }

        movimentoToSave.setSaldo(contoToUpdate.get().getSaldo());

        movimentiService.addMovimento(movimentoToSave);
        contoService.saveUpdate(contoToUpdate);
    }

    @GetMapping(value = "/all")
    public List<Movimenti> getAllMovimenti(){
        return movimentiService.getAllMovimenti();
    }

    @GetMapping(value = "/{id}")
    public List<Movimenti> getMovimentiByConto(@PathVariable(value  = "id") String idconto){
        Optional<Conto> conto = contoService.findById(Long.parseLong(idconto));
        //Modoficare con return solo degli ultimi 5 movimenti
        return movimentiService.getAllMovimentiByConto(conto);
    }

}

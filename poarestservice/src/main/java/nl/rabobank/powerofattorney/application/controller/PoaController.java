package nl.rabobank.powerofattorney.application.controller;


import nl.rabobank.powerofattorney.application.model.Poa;
import nl.rabobank.powerofattorney.application.repository.PoaRepository;
import nl.rabobank.powerofattorney.application.service.PoaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PoaController {
    private PoaRepository repository;

    public PoaController(PoaRepository repository) {
        this.repository = repository;
    }

    private final PoaService poaService = new PoaService();

    @GetMapping("/power-of-attorneys")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Poa> retrievePoas() throws Exception {
        return poaService.retrievePoas();
    }

    @GetMapping("/power-of-attorneys/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Poa retrievePoa(@PathVariable("id") String poaId) throws Exception {
        return poaService.retrievePoa(poaId);
    }

//    @GetMapping("/debit-cards/{id}")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Poa retrieveDebitcard(@PathVariable("id") String id) throws Exception {
//        return poaService.retrievePoa(id);
//    }
//
//    @GetMapping("/credit-cards/{id}")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Poa retrieveCreditcard(@PathVariable("id") String id) throws Exception {
//        return poaService.retrievePoa(id);
//    }
//
//    @GetMapping("/accounts/{id}")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Poa retrieveAccount(@PathVariable("id") String id) throws Exception {
//        return poaService.retrievePoa(id);
//    }


}

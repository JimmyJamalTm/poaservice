package nl.rabobank.powerofattorney.application.controller;


import nl.rabobank.powerofattorney.application.model.Debitcard;
import nl.rabobank.powerofattorney.application.model.Poa;
import nl.rabobank.powerofattorney.application.repository.DebitcardRepository;
import nl.rabobank.powerofattorney.application.repository.PoaRepository;
import nl.rabobank.powerofattorney.application.service.DebitcardService;
import nl.rabobank.powerofattorney.application.service.PoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DebitcardController {

    public DebitcardController() {
    }

    @Autowired
    private  DebitcardService debitcardService;

    @GetMapping("/debit-cards/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Debitcard retrieveDebitcard(@PathVariable("id") String id) throws Exception {
        return debitcardService.retrieveDebitcard(id);
    }
}

package nl.rabobank.powerofattorney.application.controller;


import nl.rabobank.powerofattorney.application.model.Creditcard;
import nl.rabobank.powerofattorney.application.model.Poa;
import nl.rabobank.powerofattorney.application.repository.CreditcardRepository;
import nl.rabobank.powerofattorney.application.repository.PoaRepository;
import nl.rabobank.powerofattorney.application.service.CreditcardService;
import nl.rabobank.powerofattorney.application.service.PoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditcardController {

    public CreditcardController() {
    }

    @Autowired
    private CreditcardService creditcardService;

    @GetMapping("/credit-cards/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Creditcard retrieveCreditcard(@PathVariable("id") String id) throws Exception {
        return creditcardService.retrieveCreditcard(id);
    }
}

package nl.rabobank.powerofattorney.application.controller;


import nl.rabobank.powerofattorney.application.model.Account;
import nl.rabobank.powerofattorney.application.model.Poa;
import nl.rabobank.powerofattorney.application.repository.AccountRepository;
import nl.rabobank.powerofattorney.application.repository.PoaRepository;
import nl.rabobank.powerofattorney.application.service.AccountService;
import nl.rabobank.powerofattorney.application.service.PoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    public AccountController() {

    }

    @Autowired
    private  AccountService accountService;

    @GetMapping("/accounts/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Account retrieveAccount(@PathVariable("id") String id) throws Exception {
        return accountService.retrieveAccount(id);
    }
}

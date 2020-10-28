package nl.rabobank.powerofattorney.application.controller;


import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.config.AuthenticationBean;
import nl.rabobank.powerofattorney.application.model.Poa;
import nl.rabobank.powerofattorney.application.repository.PoaRepository;
import nl.rabobank.powerofattorney.application.service.PoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PoaController {

    public PoaController() {
    }

    @Autowired
    private PoaService poaService;

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

    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
}

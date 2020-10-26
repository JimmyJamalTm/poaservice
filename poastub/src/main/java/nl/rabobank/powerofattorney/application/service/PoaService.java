package main.java.nl.rabobank.powerofattorney.application.service;

import nl.rabobank.powerofattorney.application.model.Poa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface PoaService extends JpaRepository<Poa, Long> {
}


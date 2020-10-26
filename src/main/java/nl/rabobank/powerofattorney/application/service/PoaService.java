package nl.rabobank.powerofattorney.application.service;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface PoaService extends JpaRepository<Record, Long> {
}


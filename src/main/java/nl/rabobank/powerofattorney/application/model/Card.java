package nl.rabobank.powerofattorney.application.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Card {
    // TODO setters mogen niet
    @Id
    private final long id;
    // TODO enum
    private final String type;
}

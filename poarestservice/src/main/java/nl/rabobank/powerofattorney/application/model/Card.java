package nl.rabobank.powerofattorney.application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Card {
    // TODO vertaalslag id naar Long
    @Id
    private String id;
    // TODO enum
    private String type;
}

package nl.rabobank.powerofattorney.application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Card {
    @Id
    private String id;
    private String type;
}

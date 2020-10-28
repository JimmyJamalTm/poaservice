package nl.rabobank.powerofattorney.application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Card {
    @Id
    private String id;
    private String type;

    public boolean isDebitCard() {
        return Objects.equals(type, "DEBIT_CARD");
    }
}

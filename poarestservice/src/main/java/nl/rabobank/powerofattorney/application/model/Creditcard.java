package nl.rabobank.powerofattorney.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Creditcard {
    @Id
    private String id;
    private String status;
    private Long cardNumber;
    private Long sequenceNumber;
    private String cardHolder;
    private Long  monthlyLimit;
}

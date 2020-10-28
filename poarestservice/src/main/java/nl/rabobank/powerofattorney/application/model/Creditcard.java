package nl.rabobank.powerofattorney.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Creditcard {
    @Id
    private String id;
    private String status;
    private Long cardNumber;
    private Long sequenceNumber;
    private String cardHolder;
    private Long  monthlyLimit;

    public boolean isActive() {
        return Objects.equals(status, "ACTIVE");
    }
}

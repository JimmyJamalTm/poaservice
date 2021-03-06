package nl.rabobank.powerofattorney.application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Debitcard {
    // TODO: atm en poslimit fixen
    @Id
    private String id;
    private String status;
    private Long cardNumber;
    private Long sequenceNumber;
    private String cardHolder;
    @JoinColumn(name = "atm_limit_id")
    @OneToOne
    private AtmLimit atmLimit;
    @JoinColumn(name = "pos_limit_id")
    @OneToOne
    private PosLimit posLimit;
    private Boolean contactless;

    public boolean isActive() {
        return Objects.equals(status, "ACTIVE");
    }

}

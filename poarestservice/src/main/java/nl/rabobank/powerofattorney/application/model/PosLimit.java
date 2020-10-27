package nl.rabobank.powerofattorney.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class PosLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Long limit;
    private String periodUnit;
}

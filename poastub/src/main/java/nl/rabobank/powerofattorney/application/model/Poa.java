package nl.rabobank.powerofattorney.application.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Poa {
    // TODO setters mogen niet
    @Id
    private final long id;
    private final String grantor;
    private final String grantee;
    private final String account;
    // TODO enum?
    private final String direction;
    // TODO enum?
    private final List<String> authorizations;
    private final List<Card> cards;
}

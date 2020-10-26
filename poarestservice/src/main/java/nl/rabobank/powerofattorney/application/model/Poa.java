package nl.rabobank.powerofattorney.application.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

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
    private final ArrayList<String> authorizations;
    private final ArrayList<Card> cards;
}

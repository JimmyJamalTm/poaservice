package nl.rabobank.powerofattorney.application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor
public class Poa {
    // TODO vertaalslag id naar Long
    @Id
    private String id;
    private String grantor;
    private String grantee;
    private String account;
    // TODO enum?
    private String direction;
    // TODO enum?
    private  ArrayList<String> authorizations;
    private  ArrayList<Card> cards;


}

package nl.rabobank.powerofattorney.application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Poa {
    @Id
    private String id;
    private String grantor;
    private String grantee;
    private String account;
    private String direction;
    private  ArrayList<String> authorizations;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    private ArrayList<Card> cards;


}

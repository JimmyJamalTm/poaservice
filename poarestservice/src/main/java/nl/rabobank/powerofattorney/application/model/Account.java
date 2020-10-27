package nl.rabobank.powerofattorney.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Account {
     @Id
     private String id;
     private String owner;
     private Long balance;
     private String created;
     private String ended;
}

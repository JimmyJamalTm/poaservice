package nl.rabobank.powerofattorney.application.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.exception.UnMarshallingErrorHandler;
import nl.rabobank.powerofattorney.application.model.*;
import nl.rabobank.powerofattorney.application.util.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class PoaService {

    ObjectMapper objectMapper = new ObjectMapper();
    DeserializationProblemHandler deserializationProblemHandler = new UnMarshallingErrorHandler();
    AccountService accountService = new AccountService();
    CreditcardService creditcardService = new CreditcardService();
    DebitcardService debitcardService = new DebitcardService();

    public List<Poa> retrievePoas() throws Exception {
        final String uri = "http://localhost:8080/power-of-attorneys";
        log.info("Attempting to retrieve power of attorneys");
        String result = Util.getJsonMessage(uri);
        objectMapper.addHandler(deserializationProblemHandler);
        List<Poa> listPoa = objectMapper.readValue(result, new TypeReference<>(){});

        return listPoa;

   }

   public Poa retrievePoa(String poaId) throws Exception {
       final String uri = "http://localhost:8080/power-of-attorneys/" + poaId;
       log.info("Attempting to retrieve power of attorney with id: " + poaId);
       String result = Util.getJsonMessage(uri);
       objectMapper.addHandler(deserializationProblemHandler);
       Poa poa = objectMapper.readValue(result, Poa.class);

       Account account = accountService.retrieveAccount(poa.getAccount().substring(8));

       if(account.getEnded() != null) {
           poa.setAccount(null);
       }

       checkCards(poa);

       return poa;

   }

    private Poa checkCards(Poa poa) {
        ArrayList<Card> activeCards = new ArrayList<>();

        poa.getCards().forEach(card -> {
            if(card.getType().equals("DEBIT_CARD")){
                try {
                    Debitcard debitcard = debitcardService.retrieveDebitcard(card.getId());
                    if(debitcard.getStatus().equals("ACTIVE")){
                        activeCards.add(card);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Creditcard creditcard = creditcardService.retrieveCreditcard(card.getId());
                    if(creditcard.getStatus().equals("ACTIVE")){
                        activeCards.add(card);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            poa.setCards(activeCards);

        });
        return poa;
    }


}



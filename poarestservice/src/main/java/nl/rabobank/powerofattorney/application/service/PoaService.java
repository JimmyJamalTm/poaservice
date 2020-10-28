package nl.rabobank.powerofattorney.application.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class PoaService {

    @Autowired
    @Qualifier("jsonMapper")
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AccountService accountService;

    @Autowired
    CreditcardService creditcardService;

    @Autowired
    DebitcardService debitcardService;

    public List<Poa> retrievePoas() throws Exception {
        final String uri = "http://localhost:8080/power-of-attorneys";
        log.info("Attempting to retrieve power of attorneys");
        String result = restTemplate.getForObject(uri, String.class);
        List<Poa> listPoa = objectMapper.readValue(result, new TypeReference<>() {
        });

        return listPoa;
    }

    public Poa retrievePoa(String poaId) throws Exception {
        final String uri = "http://localhost:8080/power-of-attorneys/" + poaId;
        log.info("Attempting to retrieve power of attorney with id: " + poaId);
        String result = restTemplate.getForObject(uri, String.class);
        Poa poa = objectMapper.readValue(result, Poa.class);
        Account account = accountService.retrieveAccount(poa.getAccount().substring(8));

        if (account.getEnded() != null) {
            poa.setAccount(null);
        }
        if (poa.getCards() != null) {
            checkCards(poa);
        }
        return poa;
    }

    private Poa checkCards(Poa poa) {
        ArrayList<Card> activeCards = new ArrayList<>();

        poa.getCards().stream()
                .forEach(card -> {
                            if (checkActiveCard(card)) {
                                activeCards.add(card);
                            }
                        }
                );
        poa.setCards(activeCards);
        return poa;
    }

    private boolean checkActiveCard(Card card) {
        if(card.getType().equals("DEBIT_CARD")){
            if (debitcardCheck(card)) {
                return true;
            }
        }
        if(card.getType().equals("CREDIT_CARD")){
            if (creditcardCheck(card)) {
                return true;
            }
        }
        return false;
    }

    private boolean debitcardCheck(Card card) {
        try {
            Debitcard debitcard = debitcardService.retrieveDebitcard(card.getId());
            if (debitcard.getStatus().equals("ACTIVE")) {
                return true;
            }
        } catch (Exception e) {
            log.error("not able to retrieve debit card information");
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean creditcardCheck(Card card) {
        try {
            Creditcard creditcard = creditcardService.retrieveCreditcard(card.getId());
            if(creditcard.getStatus().equals("ACTIVE")){
                return true;
            }
        } catch (Exception e) {
            log.error("not able to retrieve credit card information");
            throw new RuntimeException(e);
        }
        return false;
    }
}



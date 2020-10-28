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
import java.util.Optional;
import java.util.stream.Collectors;


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
        List<Poa> listPoa = objectMapper.readValue(result, new TypeReference<>(){});

        return listPoa;
    }

    public Poa retrievePoa(String poaId) throws Exception {
        Account account = null;
        final String uri = "http://localhost:8080/power-of-attorneys/" + poaId;
        log.info("Attempting to retrieve power of attorney with id: " + poaId);
        String result = restTemplate.getForObject(uri, String.class);
        Poa poa = objectMapper.readValue(result, Poa.class);
        if (poa.getAccount() != null) {
            account = accountService.retrieveAccount(poa.getAccount().substring(8));
        }
        if (account != null && account.getEnded() != null) {
            poa.setAccount(null);
        }
        if (poa.getCards() != null) {
            checkCards(poa);
        }
        return poa;
    }

    private Poa checkCards(final Poa poa) {
        final var activeCards = poa.getCards().stream()
                .filter(card -> {
                    if (card.isDebitCard()) {
                        return debitcardCheck(card)
                                .map(Debitcard::isActive)
                                .orElse(false);
                    }

                    return creditcardCheck(card)
                            .map(Creditcard::isActive)
                            .orElse(false);
                })
                .collect(Collectors
                        .toCollection(ArrayList::new));
        poa.setCards(activeCards);

        return poa;
    }

    private Optional<Debitcard> debitcardCheck(Card card) {
        Debitcard debitcard = null;
        try {
            debitcard = debitcardService.retrieveDebitcard(card.getId());
            if (debitcard.getStatus().equals("ACTIVE")) {
                return Optional.of(debitcard);
            }
        } catch (Exception e) {
            log.error("not able to retrieve debit card information");
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(debitcard);
    }

    private Optional<Creditcard> creditcardCheck(Card card) {
        Creditcard creditcard = null;
        try {
            creditcard = creditcardService.retrieveCreditcard(card.getId());
            if(creditcard.getStatus().equals("ACTIVE")){
                return Optional.of(creditcard);
            }
        } catch (Exception e) {
            log.error("not able to retrieve credit card information");
            throw new RuntimeException(e);
        }
       return Optional.ofNullable(creditcard);
    }
}



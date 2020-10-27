package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.powerofattorney.application.model.Debitcard;
import nl.rabobank.powerofattorney.application.util.Util;

public class DebitcardService {

    ObjectMapper objectMapper = new ObjectMapper();

    public Debitcard retrieveDebitcard(String cardId) throws Exception {
        // TODO : error handling
        final String uri = "http://localhost:8080//debit-cards/" + cardId;
        String result = Util.getJsonMessage(uri);
        Debitcard debitcard = objectMapper.readValue(result, Debitcard.class);

        return debitcard;

    }



}

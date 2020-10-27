package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.powerofattorney.application.model.Creditcard;
import nl.rabobank.powerofattorney.application.util.Util;

public class CreditcardService {

    ObjectMapper objectMapper = new ObjectMapper();

    public Creditcard retrieveCreditcard(String cardId) throws Exception {
        // TODO : error handling
        final String uri = "http://localhost:8080/credit-cards/" + cardId;
        String result = Util.getJsonMessage(uri);
        Creditcard creditcard = objectMapper.readValue(result, Creditcard.class);

        return creditcard;

    }



}

package nl.rabobank.powerofattorney.application.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.exception.UnMarshallingErrorHandler;
import nl.rabobank.powerofattorney.application.model.Poa;
import nl.rabobank.powerofattorney.application.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PoaService {


    ObjectMapper objectMapper = new ObjectMapper();
    DeserializationProblemHandler deserializationProblemHandler = new UnMarshallingErrorHandler();

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

       return poa;

   }


}



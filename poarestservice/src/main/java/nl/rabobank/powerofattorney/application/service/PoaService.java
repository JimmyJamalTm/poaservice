package nl.rabobank.powerofattorney.application.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.powerofattorney.application.model.Poa;
import nl.rabobank.powerofattorney.application.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
//@Transactional
@Service
public class PoaService {

//    @Value("${configurables.poa-url}")
//    private String uri;

    ObjectMapper objectMapper = new ObjectMapper();

    public List<Poa> retrievePoas() throws Exception {
        // TODO : error handling
        final String uri = "http://localhost:8080/power-of-attorneys";
        String result = Util.getJsonMessage(uri);
        List<Poa> listPoa = objectMapper.readValue(result, new TypeReference<List<Poa>>(){});

        return listPoa;

   }

   public Poa retrievePoa(String poaId) throws Exception {
       // TODO : error handling
       final String uri = "http://localhost:8080/power-of-attorneys/" + poaId;
       String result = Util.getJsonMessage(uri);
       Poa poa = objectMapper.readValue(result, Poa.class);

       return poa;

   }


}



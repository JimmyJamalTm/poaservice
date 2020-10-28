package nl.rabobank.powerofattorney.application.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.powerofattorney.application.model.Poa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoaServiceTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private PoaService poaService;

    @Test
    void testRetrievePoas() throws Exception{
        List<Poa> testPoaList = new ArrayList<>();
        Poa poa = new Poa();
        poa.setId("testId");
        testPoaList.add(poa);
        final String uri = "http://localhost:8080/power-of-attorneys" ;
        final String testString = "test";

        when(restTemplate.getForObject(eq(uri), eq(String.class))).thenReturn(testString);
        when(objectMapper.readValue(eq(testString), any(TypeReference.class))).thenReturn(testPoaList);

        assertEquals(poaService.retrievePoas(), testPoaList);
    }

    @Test
    void testRetrievePoa() throws Exception{
        Poa poa = new Poa();
        poa.setId("testId");
        final String uri = "http://localhost:8080/power-of-attorneys/" + poa.getId() ;
        final String testString = "test";

        when(restTemplate.getForObject(eq(uri), eq(String.class))).thenReturn(testString);
        when(objectMapper.readValue(eq(testString), eq(Poa.class))).thenReturn(poa);

        assertEquals(poaService.retrievePoa(poa.getId()), poa);
    }

}



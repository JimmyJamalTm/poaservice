package nl.rabobank.powerofattorney.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import nl.rabobank.powerofattorney.application.exception.RestTemplateErrorHandler;
import nl.rabobank.powerofattorney.application.exception.UnMarshallingErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    DeserializationProblemHandler deserializationProblemHandler = new UnMarshallingErrorHandler();

    @Bean(name = "jsonMapper")
    @Primary
    public ObjectMapper jsonMapper() {
        return new ObjectMapper().addHandler(deserializationProblemHandler);
    }

    @Bean(name = "customRestTemplate")
    @Primary
    public RestTemplate customRestTemplate() {
        RestTemplate customRestTemplate = new RestTemplate();
        customRestTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return customRestTemplate;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(jsonMapper()));
    }
}

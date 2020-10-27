package nl.rabobank.powerofattorney.application.exception;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UnMarshallingErrorHandler extends DeserializationProblemHandler {
    @Override
    public boolean handleUnknownProperty(DeserializationContext ctxt,
                                         JsonParser jp, JsonDeserializer deserializer,
                                         Object beanOrClass, String propertyName) throws IOException {
        super.handleUnknownProperty(ctxt, jp, deserializer, beanOrClass,
                propertyName);
        log.error("Property with name '" + propertyName + "' doesn't exist in Class of type '" + beanOrClass.getClass().getName() + "'");
        return true;
    }
}

package com.intercorp.backendchallenge.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.intercorp.backendchallenge.config.ApplicationProperties;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

  @Autowired
  private ApplicationProperties applicationProperties;

  @Override
  public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
    jgen.writeString(value
        .setScale(applicationProperties.getMaxDecimals(), BigDecimal.ROUND_HALF_UP).toString());
  }
}


package com.intercorp.backendchallenge.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.intercorp.backendchallenge.config.ApplicationProperties;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BigDecimalSerializerTest {

  @InjectMocks
  private BigDecimalSerializer bigDecimalSerializer;

  @Mock
  private ApplicationProperties applicationProperties;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void serializeTest() throws Exception {
    // given
    Mockito.when(applicationProperties.getMaxDecimals()).thenReturn(6);
    Writer jsonWriter = new StringWriter();
    JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
    SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();

    // when
    BigDecimal value = BigDecimal.TEN;
    bigDecimalSerializer.serialize(value, jsonGenerator, serializerProvider);

    // then
    jsonGenerator.flush();
    MatcherAssert.assertThat(jsonWriter.toString(),
        CoreMatchers.is(CoreMatchers.equalTo("\"10.000000\"")));

  }

}

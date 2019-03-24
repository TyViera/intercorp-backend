package com.intercorp.backendchallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties
public class ApplicationProperties {

  @Value("${application.max-decimals}")
  private Integer maxDecimals;

}

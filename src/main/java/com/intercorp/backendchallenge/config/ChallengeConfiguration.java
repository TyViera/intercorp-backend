package com.intercorp.backendchallenge.config;

import com.intercorp.backendchallenge.model.Client;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChallengeConfiguration {

  @Bean
  public List<Client> clientDatabase() {
    return new ArrayList<>();
  }

}

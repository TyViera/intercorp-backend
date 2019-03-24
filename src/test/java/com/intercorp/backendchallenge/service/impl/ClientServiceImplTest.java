package com.intercorp.backendchallenge.service.impl;

import com.github.javafaker.Faker;
import com.intercorp.backendchallenge.config.ApplicationProperties;
import com.intercorp.backendchallenge.enums.ErrorEnums;
import com.intercorp.backendchallenge.error.ChallengeException;
import com.intercorp.backendchallenge.model.Client;
import com.intercorp.backendchallenge.repository.ClientRepository;
import com.intercorp.backendchallenge.util.ChallengeUtil;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

  private static Integer initSize = 10;

  @InjectMocks
  private ClientServiceImpl clientService;

  @Mock
  private ApplicationProperties applicationProperties;

  @Mock
  private ClientRepository clientRepository;

  @Mock
  private ChallengeUtil challengeUtil;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = ChallengeException.class)
  public void listErrorTest() {
    // given
    ChallengeException challengeException = new ChallengeException(ErrorEnums.UNKNOWN);
    Mockito.when(clientRepository.list()).thenReturn(createInitList());
    Mockito.when(challengeUtil.getDeathDate(ArgumentMatchers.any(LocalDate.class),
        ArgumentMatchers.anyInt())).thenThrow(challengeException);

    // when
    clientService.list();
  }

  @Test(expected = ChallengeException.class)
  public void kpideNoDataErrorTest() {
    // given
    Mockito.when(clientRepository.list()).thenReturn(Collections.emptyList());

    // when
    clientService.kpide();
  }

  @Test(expected = ChallengeException.class)
  public void kpideUniqueDataErrorTest() {
    // given
    initSize = 1;
    Mockito.when(clientRepository.list()).thenReturn(createInitList());

    // when
    clientService.kpide();
  }

  private List<Client> createInitList() {
    Faker faker = new Faker();
    return IntStream.range(0, initSize).mapToObj(x -> {
      Client client = new Client();
      client.setBornDate(faker.date().birthday().toInstant().atZone(ZoneOffset.UTC).toLocalDate());
      client.setAge(new SecureRandom().nextInt());
      client.setLastName(faker.name().lastName());
      client.setName(faker.name().name());
      return client;
    }).collect(Collectors.toList());
  }

}

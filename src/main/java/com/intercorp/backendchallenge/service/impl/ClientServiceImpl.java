package com.intercorp.backendchallenge.service.impl;

import com.intercorp.backendchallenge.config.ApplicationProperties;
import com.intercorp.backendchallenge.enums.ErrorEnums;
import com.intercorp.backendchallenge.error.ChallengeException;
import com.intercorp.backendchallenge.model.Client;
import com.intercorp.backendchallenge.model.InformationClients;
import com.intercorp.backendchallenge.repository.ClientRepository;
import com.intercorp.backendchallenge.service.ClientService;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ApplicationProperties applicationProperties;

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public List<Client> list() throws ChallengeException {
    try {
      return clientRepository.list();
    } catch (Exception e) {
      log.error("An error ocurred in ClientService.list {} ", e);
      throw new ChallengeException(ErrorEnums.UNKNOWN);
    }
  }

  @Override
  public void save(Client client) throws ChallengeException {
    try {
      clientRepository.save(client);
    } catch (Exception e) {
      log.error("An error ocurred in ClientService.save {} ", e);
      throw new ChallengeException(ErrorEnums.UNKNOWN);
    }
  }

  @Override
  public InformationClients kpide() throws ChallengeException {
    List<Client> clientList = clientRepository.list();
    if (clientList.isEmpty()) {
      throw new ChallengeException(ErrorEnums.NO_DATA);
    }
    Integer listSize = clientList.size();
    if (listSize == 1) {
      throw new ChallengeException(ErrorEnums.INSUFFICIENT_DATA);
    }

    try {
      MathContext dec128 = MathContext.DECIMAL128;
      BigDecimal size = new BigDecimal(listSize);
      BigDecimal sum = clientList.stream().map(client -> new BigDecimal(client.getAge()))
          .reduce((x, y) -> x.add(y)).orElse(BigDecimal.ZERO);
      log.info("The sum was: " + sum);

      BigDecimal average = sum.divide(size, dec128);
      log.info("The average was: " + average);

      BigDecimal variance = clientList.stream().map(client -> new BigDecimal(client.getAge()))
          .map(age -> age.subtract(average, dec128)).map(age -> age.pow(2, dec128))
          .reduce((x, y) -> x.add(y)).orElse(BigDecimal.ZERO)
          .divide(size.subtract(BigDecimal.ONE, dec128), dec128);
      log.info("The variance was: " + variance);
      BigDecimal deviation = new BigDecimal(Math.sqrt(variance.doubleValue()), dec128);
      deviation = deviation.setScale(applicationProperties.getMaxDecimals(), RoundingMode.HALF_UP);

      log.info("The deviation was: " + deviation);
      InformationClients informationClients = new InformationClients();
      informationClients.setAverageAge(average);
      informationClients.setStandardDeviationAge(deviation);
      return informationClients;
    } catch (Exception e) {
      log.error("An error ocurred in ClientService.kpide {} ", e);
      throw new ChallengeException(ErrorEnums.UNKNOWN);
    }
  }

}

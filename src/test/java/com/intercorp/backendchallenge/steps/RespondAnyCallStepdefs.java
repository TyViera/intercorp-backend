package com.intercorp.backendchallenge.steps;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.intercorp.backendchallenge.SpringIntegrationTest;
import com.intercorp.backendchallenge.config.ApplicationProperties;
import com.intercorp.backendchallenge.model.Client;
import com.intercorp.backendchallenge.model.InformationClients;
import com.intercorp.backendchallenge.service.ClientService;
import com.intercorp.backendchallenge.util.ChallengeUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RespondAnyCallStepdefs extends SpringIntegrationTest {

  private List<Client> clientList;
  private InformationClients informationClients;

  @Autowired
  private ApplicationProperties applicationProperties;

  @Autowired
  private ClientService clientService;

  @Autowired
  private ChallengeUtil challengeUtil;

  @Autowired
  private List<Client> clientDatabase;

  @After
  public void tearDown() {
    clientDatabase.clear();
  }

  @Given("^there are (\\d+) client records in database$")
  public void there_are_client_records_in_database(int initClienteNumber) throws Throwable {
    Faker faker = new Faker();
    Gson gson = new Gson();
    List<Client> initList = IntStream.range(0, initClienteNumber).mapToObj(x -> {
      Client client = new Client();
      client.setBornDate(faker.date().birthday().toInstant().atZone(ZoneOffset.UTC).toLocalDate());
      client.setAge(challengeUtil.getAge(client.getBornDate()));
      client.setLastName(faker.name().lastName());
      client.setName(faker.name().name());
      log.info("Created client: " + gson.toJson(client));
      return client;
    }).collect(Collectors.toList());
    clientDatabase.addAll(initList);
  }

  @Given("^there are some clients with the next ages (.*)$")
  public void there_are_some_clients_with_the_next_ages(List<Integer> ages) throws Throwable {
    Faker faker = new Faker();
    Gson gson = new Gson();
    List<Client> initList = ages.stream().map(age -> {
      Client client = new Client();
      client.setBornDate(
          faker.date().birthday(age, age).toInstant().atZone(ZoneOffset.UTC).toLocalDate());
      client.setAge(age);
      client.setLastName(faker.name().lastName());
      client.setName(faker.name().name());
      log.info("Crated client with age: " + age + " and data: " + gson.toJson(client));
      return client;
    }).collect(Collectors.toList());
    clientDatabase.addAll(initList);
  }

  @When("^the user calls to the list operation$")
  public void the_user_calls_to_the_list_operation() throws Throwable {
    clientList = clientService.list();
    log.info("The result list was: " + new Gson().toJson(clientList));
  }

  @When("^the user calls to the create operation$")
  public void the_user_calls_to_the_create_operation() throws Throwable {
    Faker faker = new Faker();
    Client client = new Client();
    client.setBornDate(faker.date().birthday().toInstant().atZone(ZoneOffset.UTC).toLocalDate());
    client.setAge(challengeUtil.getAge(client.getBornDate()));
    client.setLastName(faker.name().lastName());
    client.setName(faker.name().name());
    log.info("Create operation... saving client: " + new Gson().toJson(client));
    clientService.save(client);
  }

  @When("^the user calls to the kpid operation$")
  public void the_user_calls_to_the_kpid_operation() throws Throwable {
    informationClients = clientService.kpide();
    log.info("The result information clients was: " + new Gson().toJson(informationClients));
  }

  @Then("^the user receives (\\d+) client records$")
  public void the_user_receives_client_records(int clienteNumber) throws Throwable {
    Assert.assertNotNull("result client list was null!", clientList);
    MatcherAssert.assertThat("result client list was empty!", clientList,
        CoreMatchers.not(IsEmptyCollection.empty()));
    MatcherAssert.assertThat("result client list does not have the expected size!", clientList,
        IsCollectionWithSize.hasSize(clienteNumber));
  }

  @Then("^the user receives the next values for (\\d+.\\d+) and (\\d+.\\d+)$")
  public void the_user_receives_the_next_values_for_and(BigDecimal average,
      BigDecimal standard_deviation) throws Throwable {
    Assert.assertNotNull("result information list for kpid operation was null!",
        informationClients);
    Assert.assertNotNull("result average was null!", informationClients.getAverageAge());
    Assert.assertNotNull("result standard deviation was null!",
        informationClients.getStandardDeviationAge());
    Assert.assertTrue("result average does not have the expected value!",
        compareValues(average, informationClients.getAverageAge()));
    Assert.assertTrue("result standard deviation does not have the expected value!",
        compareValues(standard_deviation, informationClients.getStandardDeviationAge()));
  }

  private Boolean compareValues(BigDecimal expected, BigDecimal actual) {
    expected = expected.setScale(applicationProperties.getMaxDecimals(), RoundingMode.HALF_UP);
    actual = actual.setScale(applicationProperties.getMaxDecimals(), RoundingMode.HALF_UP);
    return expected.compareTo(actual) == 0;
  }

}

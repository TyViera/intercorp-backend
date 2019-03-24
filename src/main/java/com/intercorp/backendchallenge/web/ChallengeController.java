package com.intercorp.backendchallenge.web;

import com.intercorp.backendchallenge.error.ChallengeException;
import com.intercorp.backendchallenge.model.Client;
import com.intercorp.backendchallenge.model.InformationClients;
import com.intercorp.backendchallenge.model.ResponseClient;
import com.intercorp.backendchallenge.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Challenge controller for list, kpi and create operations",
    tags = "Challenge controller")
@RestController
public class ChallengeController {

  @Autowired
  private ClientService clientService;

  @ApiOperation(value = "Get all clients created previously", response = List.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Succesfully operation"),
      @ApiResponse(code = 500, message = "An error occurred", response = ResponseClient.class)})
  @GetMapping(value = "/listclientes", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Client> getAllClients() throws ChallengeException {
    return clientService.list();
  }

  @ApiOperation(value = "Get the average and standard deviation from client list",
      response = InformationClients.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Succesfully operation"),
      @ApiResponse(code = 500, message = "An error occurred", response = ResponseClient.class)})
  @GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
  public InformationClients getClientsKpi() throws ChallengeException {
    return clientService.kpide();
  }

  @ApiOperation(value = "Create a new client with some data", response = Void.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Succesfully operation"),
      @ApiResponse(code = 500, message = "An error occurred", response = ResponseClient.class)})
  @PostMapping(value = "/createcliente", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void createClient(@RequestBody Client client) throws ChallengeException {
    clientService.save(client);
  }

}

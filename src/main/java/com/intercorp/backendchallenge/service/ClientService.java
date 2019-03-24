package com.intercorp.backendchallenge.service;

import com.intercorp.backendchallenge.error.ChallengeException;
import com.intercorp.backendchallenge.model.Client;
import com.intercorp.backendchallenge.model.InformationClients;
import java.util.List;

public interface ClientService {

  public List<Client> list() throws ChallengeException;

  public void save(Client client) throws ChallengeException;

  public InformationClients kpide() throws ChallengeException;

}

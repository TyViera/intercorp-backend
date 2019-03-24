package com.intercorp.backendchallenge.repository.impl;

import com.intercorp.backendchallenge.model.Client;
import com.intercorp.backendchallenge.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

  @Autowired
  private List<Client> clientDatabase;

  @Override
  public List<Client> list() {
    return clientDatabase;
  }

  @Override
  public void save(Client client) {
    clientDatabase.add(client);
  }

}

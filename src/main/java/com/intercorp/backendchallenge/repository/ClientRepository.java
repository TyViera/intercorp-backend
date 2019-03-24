package com.intercorp.backendchallenge.repository;

import com.intercorp.backendchallenge.model.Client;
import java.util.List;

public interface ClientRepository {

  public List<Client> list();

  public void save(Client client);

}

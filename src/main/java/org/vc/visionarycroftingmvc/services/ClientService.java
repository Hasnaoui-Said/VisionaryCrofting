package org.vc.visionarycroftingmvc.services;


import org.vc.visionarycroftingmvc.models.entity.Client;

import java.util.List;

public interface ClientService {

    int deleteByEmail(String email);

    Client findByEmail(String email);

    List<Client> findAll();

    Client getOne(Long aLong);

    Client save(Client client);

    boolean existsByEmail(String e);

    Client update(Client client);
}

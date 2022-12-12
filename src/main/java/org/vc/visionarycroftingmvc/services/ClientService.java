package org.vc.visionarycroftingmvc.services;


import org.vc.visionarycroftingmvc.models.entity.Client;
import org.vc.visionarycroftingmvc.voDTO.ClientDto;

import java.util.List;

public interface ClientService {

    int deleteByEmail(String email);

    Client findByEmail(String email);

    List<Client> findAll();

    Client getOne(Long aLong);

    ClientDto save(ClientDto client);

    boolean existsByEmail(String e);

    Client update(Client client);

    Client login(String login, String psw);
}

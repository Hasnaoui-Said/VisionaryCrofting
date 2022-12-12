package org.vc.visionarycroftingmvc.services.impl;

import org.vc.visionarycroftingmvc.models.entity.Client;
import org.vc.visionarycroftingmvc.repository.ClientDao;
import org.vc.visionarycroftingmvc.services.ClientService;
import org.vc.visionarycroftingmvc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vc.visionarycroftingmvc.util.TransfertDto;
import org.vc.visionarycroftingmvc.voDTO.ClientDto;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public int deleteByEmail(String email) {
        return clientDao.deleteByEmail(email);
    }

    @Override
    public Client findByEmail(String email) {
        return clientDao.findByEmail(email);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Deprecated
    @Override
    public Client getOne(Long aLong) {
        return clientDao.getOne(aLong);
    }

    @Override
    public ClientDto save(ClientDto client) {
        if (client == null) return null;
        if (this.findByEmail(client.getEmail()) != null || StringUtil.isEmpty(client.getEmail()))
            return null;
//            throw new BadRequestException("Email is token!!");
        Client client1 = TransfertDto.clientDtoToCleint(client);
        Client clientSave = clientDao.save(client1);
        return TransfertDto.clientToDto(clientSave);
    }

    @Override
    @Transactional
    public Client update(Client client) {
        if (!this.existsByEmail(client.getEmail()))
            return null;
//            throw new BadRequestException("Client with this parameter not found!!");
        Client byEmail = this.findByEmail(client.getEmail());
        if (!StringUtil.isEmpty(client.getPassword()))
            byEmail.setPassword(client.getPassword());
        if (!StringUtil.isEmpty(client.getPhone()))
            byEmail.setPhone(client.getPhone());
        return byEmail;
    }

    @Override
    public boolean existsByEmail(String e) {
        return clientDao.existsByEmail(e);
    }

    @Override
    public Client login(String login, String psw){
        if (!this.existsByEmail(login)){
            return null;
        }
        Client client = this.findByEmail(login);
        if (!client.getPassword().equals(psw)) return null;
        return client;
    }
}

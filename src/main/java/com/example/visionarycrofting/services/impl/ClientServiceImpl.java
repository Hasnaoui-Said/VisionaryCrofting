package com.example.visionarycrofting.services.impl;

import com.example.visionarycrofting.entity.Client;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.exception.NotFoundException;
import com.example.visionarycrofting.repository.ClientDao;
import com.example.visionarycrofting.services.ClientService;
import com.example.visionarycrofting.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Client save(Client client) {
        if (this.findByEmail(client.getEmail()) != null || StringUtil.isEmpty(client.getEmail()))
            throw new BadRequestException("Email is token!!");
        return clientDao.save(client);
    }

    @Override
    @Transactional
    public Client update(Client client) {
        if (!this.existsByEmail(client.getEmail()))
            throw new BadRequestException("Client whit this parameter not found!!");
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


}

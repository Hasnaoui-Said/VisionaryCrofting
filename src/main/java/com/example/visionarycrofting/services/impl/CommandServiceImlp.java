package com.example.visionarycrofting.services.impl;

import com.example.visionarycrofting.entity.Client;
import com.example.visionarycrofting.entity.Command;
import com.example.visionarycrofting.entity.CommandItem;
import com.example.visionarycrofting.entity.Product;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.repository.CommandDao;
import com.example.visionarycrofting.services.ClientService;
import com.example.visionarycrofting.services.CommadItemService;
import com.example.visionarycrofting.services.CommandService;
import com.example.visionarycrofting.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommandServiceImlp implements CommandService {
    @Autowired
    CommandDao commandDao;
    @Autowired
    CommadItemService commadItemService;
    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;

    @Override
    public Command findByRef(String ref) {
        return commandDao.findByRef(ref);
    }

    @Override
    @Transactional
    public int deleteByRef(String ref) {
        Command command = this.findByRef(ref);
        command.getCommandItems().stream().forEach(commandItem -> commadItemService.deleteByRef(commandItem.getRef()));
        return commandDao.deleteByRef(ref);
    }

    @Override
    public List<Command> findAllByClientEmailAndPrixTotalGreaterThanEqual(String email, double total) {
        return commandDao.findAllByClientEmailAndPrixTotalGreaterThanEqual(email, total);
    }

    @Override
    public List<Command> findAll() {
        return commandDao.findAll();
    }

    @Deprecated
    @Override
    public Command getOne(Long aLong) {
        return commandDao.getOne(aLong);
    }

    @Override
    public Command save(Command command) {
        String emailClient = command.getClient().getEmail();
        if (!clientService.existsByEmail(emailClient)) {
            throw new BadRequestException("Client not found!!!");
        }
        Client client = clientService.findByEmail(emailClient);
        command.setClient(client);
        if (commandDao.existsByRef(command.getRef()))
            throw new BadRequestException("Ref exist already!!");

        List<CommandItem> commandItems = command.getCommandItems()
                .stream().filter(item -> {
                            boolean exists = productService.existsByRef(item.getProduct().getRef());
                            if (exists) {
                                Product product = productService.findByRef(item.getProduct().getRef());
                                Boolean qua = product.getQuantity() >= item.getQuantity();
                                if (!qua) return false;
                                item.setProduct(product);
                                item.setPrix(product.getPrix());
                            }
                            return exists;
                        }
                ).collect(Collectors.toList());

        if (commandItems.size() == 0)
            throw new BadRequestException("Error, command items null");

        // Calculi total
        commandItems.forEach(item -> {
            double productPrix = productService.findByRef(item.getProduct().getRef()).getPrix() * item.getQuantity();
            command.setPrixTotal(command.getPrixTotal() + productPrix);
        });
        command.setDateCommand(new Date());
        command.getCommandItems().clear();
        command.setRef(UUID.randomUUID().toString());
        Command command1 = commandDao.save(command);

        commandItems.stream().forEach(commandItem -> {
            commandItem.setCommand(command1);
            CommandItem item = commadItemService.save(commandItem);
            command1.getCommandItems().add(item);
        });

        return command1;
    }

    @Override
    public List<Command> getAllByClientEmail(String email) {
        return commandDao.findAllByClientEmail(email);
    }

    @Override
    public boolean existsByRef(String ref) {
        return commandDao.existsByRef(ref);
    }

}

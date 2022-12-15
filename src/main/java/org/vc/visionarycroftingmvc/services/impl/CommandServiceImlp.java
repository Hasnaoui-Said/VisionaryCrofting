package org.vc.visionarycroftingmvc.services.impl;

import org.vc.visionarycroftingmvc.models.entity.Client;
import org.vc.visionarycroftingmvc.models.entity.Command;
import org.vc.visionarycroftingmvc.models.entity.CommandItem;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.repository.CommandDao;
import org.vc.visionarycroftingmvc.services.ClientService;
import org.vc.visionarycroftingmvc.services.CommadItemService;
import org.vc.visionarycroftingmvc.services.CommandService;
import org.vc.visionarycroftingmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
            return null;
//            throw new BadRequestException("Client not found!!!");
        }
        Client client = clientService.findByEmail(emailClient);
        command.setClient(client);
        command.setRef(UUID.randomUUID().toString());
        if (command.getRef() == null || command.getRef().equals("")) return null;
        if (commandDao.existsByRef(command.getRef())) return null;

        if (command.getCommandItems().size() == 0) {
            return null;
//            throw new BadRequestException("Add command items");
        }
        List<CommandItem> commandItems = command.getCommandItems().stream().filter(item ->
                        !commadItemService.existsByRef(item.getRef())
                ).collect(Collectors.toList())
                .stream().filter(commandItem -> commandItem.getQuantity() > 0
                        && productService.findByRef(commandItem.getProduct().getRef()).getQuantity() >= commandItem.getQuantity()
                ).collect(Collectors.toList())
                .stream().filter(item -> {
                            boolean exists = productService.existsByRef(item.getProduct().getRef());
                            if (exists) {
                                Product product = productService.findByRef(item.getProduct().getRef());
                                item.setProduct(product);
                                item.setPrix(product.getPrix());
                            }
                            return exists;
                        }
                ).collect(Collectors.toList());

        if (commandItems.size() == 0) {
            return null;
//            throw new BadRequestException("Error, doesn't exist");
        }
        // Calculi total
        commandItems.forEach(item -> {
            double productPrix = productService.findByRef(item.getProduct().getRef()).getPrix() * item.getQuantity();
            double prix = productPrix;
            command.setPrixTotal(command.getPrixTotal() + prix);
        });
        Command command1 = commandDao.save(command);
        command1.setDateCommand(new Date());
        command1.getCommandItems().clear();
        commandItems.stream().forEach(commandItem -> {
            commandItem.setCommand(command1);
            CommandItem item = commadItemService.save(commandItem);
            command1.getCommandItems().add(item);
        });

        return command1;
    }

    @Override
    public Command saveCommand(Command command) {
        if (command.getRef() != null && !command.getRef().equals("")) return null;
        if (command.getCommandItems().size() == 0)
            return null;
        if (command.getClient() == null || command.getClient().getEmail().equals(""))
            return null;
        String emailClient = command.getClient().getEmail();
        if (!clientService.existsByEmail(emailClient))
            return null;
//            throw new BadRequestException("Client not found!!!");

        Client client = clientService.findByEmail(emailClient);
        command.setClient(client);
        List<CommandItem> commandItems = command.getCommandItems()
                .stream().filter(commandItem -> {
                            Product expectedProduct = commandItem.getProduct();

                            if (expectedProduct == null && !productService.existsByRef(expectedProduct.getRef()))
                                return false;
                            Product product = productService.findByRef(commandItem.getProduct().getRef());
                            commandItem.setProduct(product);
                            commandItem.setPrix(product.getPrix());
                            return commandItem.getQuantity() > 0
                                    && product.getQuantity() >= commandItem.getQuantity();
                        }
                ).collect(Collectors.toList());

        if (commandItems.size() == 0)
            return null;
        // Calculi total
        command.setPrixTotal(0);
        commandItems.forEach(item -> {
            command.setPrixTotal(command.getPrixTotal() + item.getPrix() * item.getQuantity());
        });
        command.setDateCommand(new Date());
        command.getCommandItems().clear();
        command.setRef(UUID.randomUUID().toString());
        Command command1 = commandDao.save(command);

        commandItems.forEach(commandItem -> {
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

    @Override
    public Command update(Command command) {
        return null;
    }

    @Override
    public List<Command> findAllByClientEmail(String email) {
        return commandDao.findAllByClientEmail(email);
    }
}

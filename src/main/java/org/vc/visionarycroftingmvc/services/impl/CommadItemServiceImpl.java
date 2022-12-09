package org.vc.visionarycroftingmvc.services.impl;

import org.vc.visionarycroftingmvc.models.entity.CommandItem;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.repository.CommandItemDao;
import org.vc.visionarycroftingmvc.services.CommadItemService;
import org.vc.visionarycroftingmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommadItemServiceImpl implements CommadItemService {
    @Autowired
    CommandItemDao commandItemDao;
    @Autowired
    ProductService productService;

    @Override
    public CommandItem findByRef(String ref) {
        return commandItemDao.findByRef(ref);
    }

    @Override
    @Transactional
    public int deleteByRef(String ref) {
        return commandItemDao.deleteByRef(ref);
    }

    @Override
    public boolean existsByRef(String ref) {
        return commandItemDao.existsByRef(ref);
    }

    @Override
    public List<CommandItem> findAll() {
        return commandItemDao.findAll();
    }

    @Deprecated
    @Override
    public CommandItem getOne(Long aLong) {
        return commandItemDao.getOne(aLong);
    }

    @Override
    public CommandItem save(CommandItem commandItem) {
        if (this.existsByRef(commandItem.getRef()))
            return null;
//            throw new BadRequestException("commandItem already exist with this parameter ref = " + commandItem.getRef());
        Product product = productService.findByRef(commandItem.getProduct().getRef());
        product.setQuantity(product.getQuantity() - commandItem.getQuantity());
        productService.update(product);
        return commandItemDao.save(commandItem);
    }

    @Override
    public CommandItem update(CommandItem commandItem) {
        CommandItem command1 = this.findByRef(commandItem.getRef());
        if (command1 == null)
            return null;
//            throw new BadRequestException("commandItem not found with this parameter ref = " + commandItem.getRef());;
        return commandItemDao.save(commandItem);
    }

    @Override
    public List<CommandItem> findAllByPrixGreaterThanEqual(long prix) {
        return commandItemDao.findAllByPrixGreaterThanEqual(prix);
    }

    @Override
    public List<CommandItem> findAllByCommandRef(String refCommand) {
        return commandItemDao.findAllByCommandRef(refCommand);
    }

    @Override
    public List<CommandItem> findAllByProductRef(String ref) {
        return commandItemDao.findAllByProductRef(ref);
    }

    @Override
    public List<CommandItem> findAllByQuantityGreaterThanEqual(int quant) {
        return commandItemDao.findAllByQuantityGreaterThanEqual(quant);
    }
}

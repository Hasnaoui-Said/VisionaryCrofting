package org.vc.visionarycroftingmvc.services;

import org.vc.visionarycroftingmvc.models.entity.CommandItem;

import java.util.List;

public interface CommadItemService {

    CommandItem findByRef(String ref);

    int deleteByRef(String ref);

    boolean existsByRef(String ref);

    List<CommandItem> findAll();

    CommandItem getOne(Long aLong);

    CommandItem save(CommandItem commandItem);
    CommandItem update(CommandItem commandItem);

    List<CommandItem> findAllByPrixGreaterThanEqual(long prix);

    List<CommandItem> findAllByCommandRef(String refCommand);

    List<CommandItem> findAllByProductRef(String ref);

    List<CommandItem> findAllByQuantityGreaterThanEqual(int quant);
}

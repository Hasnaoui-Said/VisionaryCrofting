package com.example.visionarycrofting.repository;

import com.example.visionarycrofting.entity.CommandItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandItemDao extends JpaRepository<CommandItem, Long> {
    List<CommandItem> findAllByPrixGreaterThanEqual(long prix);
    List<CommandItem> findAllByCommandRef(String refCommand);
    List<CommandItem> findAllByProductRef(String ref);
    List<CommandItem> findAllByQuantityGreaterThanEqual(int quant);
    CommandItem findByRef(String ref);
    int deleteByRef(String ref);
    boolean existsByRef(String ref);

}

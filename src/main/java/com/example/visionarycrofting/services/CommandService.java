package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.Command;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommandService {
    Command findByRef(String ref);

    int deleteByRef(String ref);

    List<Command> findAllByClientEmailAndPrixTotalGreaterThanEqual(String email, double total);

    List<Command> findAll();

    @Deprecated
    Command getOne(Long aLong);

    Command save(Command command);

    List<Command> getAllByClientEmail(String email);

    boolean existsByRef(String email);

    Command update(Command command);
}

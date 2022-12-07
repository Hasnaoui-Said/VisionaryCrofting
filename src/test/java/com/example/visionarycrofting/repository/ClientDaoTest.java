package com.example.visionarycrofting.repository;

import com.example.visionarycrofting.entity.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientDaoTest {

    @Autowired
    private ClientDao clientDaoTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        clientDaoTest.deleteAll();
    }

    @Test
    void deleteByEmail() {
    }

    @Test
    void findByEmail() {

        Client employee = new Client();
        String email = "saidhasnaoui.uce@gmail.com";
        employee.setPhone("02345678");
        employee.setEmail(email);
        clientDaoTest.save(employee);

        Client expected = clientDaoTest.findByEmail(email);

//        assertSame();
    }

    @Test
    void ifExistsByEmail() {

        Client employee = new Client();
        String email = "saidhasnaoui.uce@gmail.com";
        employee.setPhone("02345678");
        employee.setEmail(email);
        clientDaoTest.save(employee);

        boolean expected = clientDaoTest.existsByEmail(email);

        assertThat(expected).isTrue();
    }
    @Test
    void ifDoesNoExistsByEmail() {
        String email = "saidhasnaoui.uce@gmail.com";

        boolean expected = clientDaoTest.existsByEmail(email);

        assertThat(expected).isFalse();
    }
}
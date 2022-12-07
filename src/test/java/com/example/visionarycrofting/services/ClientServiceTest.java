package com.example.visionarycrofting.services;

import com.example.visionarycrofting.repository.ClientDao;
import com.example.visionarycrofting.services.impl.ClientServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class ClientServiceTest {
    @Mock
    private ClientDao mockDao;
    private ClientService serviceTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        serviceTest = new ClientServiceImpl(mockDao);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canIGetAllClient() {
        serviceTest.findAll();

        verify(mockDao).findAll();
    }

    @Test
    @Disabled
    void deleteByEmail() {
    }

    @Test
    @Disabled
    void findByEmail() {
    }


    @Test
    @Disabled
    void getOne() {
    }

    @Test
    @Disabled
    void save() {
    }

    @Test
    @Disabled
    void deleteById() {
    }

    @Test
    @Disabled
    void existsByEmail() {
    }

    @Test
    @Disabled
    void update() {
    }
}
package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.Supplier;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.exception.NotFoundException;
import com.example.visionarycrofting.repository.SupplierDao;
import com.example.visionarycrofting.services.impl.SupplierServiceIml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {


    @Mock
    private SupplierDao mockDao;
    private SupplierService serviceTest;

    @BeforeEach
    void setUp() {
        serviceTest = new SupplierServiceIml(mockDao);
    }

    @Test
    void canIGetAllSupplier() {
        serviceTest.findAll();
        verify(mockDao).findAll();
    }

    @Test
    void canNotDeleteSupplierByEmail() {
        String email = "hamza@gmail.com";

        given(mockDao.existsByEmail(email)).willReturn(false);
        assertThatThrownBy(() -> serviceTest.deleteByEmail("hamza@gmail.com")).isInstanceOf(NotFoundException.class).hasMessageContaining("Bayer with email: " + email + " does not exists");
    }

    @Test
    void canDeleteSupplierByEmail() {
        String email = "hamza@gmail.com";

        given(mockDao.existsByEmail(email)).willReturn(true);
        int result = serviceTest.deleteByEmail(email);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @Disabled
    void deleteByName() {
    }

    @Test
    @Disabled
    void findByEmail() {
    }

    @Test
    @Disabled
    void findByName() {
    }


    @Test
    @Disabled
    void getOne() {
    }

    @Test
    void canIAddFournissour() {
        Supplier fournisseur = new Supplier();
        fournisseur.setName("hamza");
        fournisseur.setEmail("hamza@gmail.com");
        fournisseur.setPassword("AZERTY");
        fournisseur.setPhone("0654656432");

        serviceTest.save(fournisseur);

        ArgumentCaptor<Supplier> fournisseurArgumentCaptor = ArgumentCaptor.forClass(Supplier.class);
        verify(mockDao).save(fournisseurArgumentCaptor.capture());
        Supplier capture = fournisseurArgumentCaptor.getValue();
        assertThat(capture).isEqualTo(fournisseur);
    }

    @Test
    void canNotAddSupplierEmailToken() {
        Supplier fournisseur = new Supplier();
        fournisseur.setName("hamza");
        fournisseur.setEmail("hamza@gmail.com");
        fournisseur.setPassword("AZERTY");
        fournisseur.setPhone("0654656432");

        given(mockDao.existsByEmail(fournisseur.getEmail())).willReturn(true);
        assertThatThrownBy(() -> serviceTest.save(fournisseur))
                .isInstanceOf(BadRequestException.class).hasMessageContaining("Email " + fournisseur.getEmail() + " is token");
    }

    @Test
    void canNotAddSupplierWithEmailNotValid() {
        Supplier fournisseur = new Supplier();
        fournisseur.setName("hamza");
        fournisseur.setEmail("");
        fournisseur.setPassword("AZERTY");
        fournisseur.setPhone("0654656432");

        given(mockDao.existsByEmail(fournisseur.getEmail())).willReturn(false);
        assertThatThrownBy(() -> serviceTest.save(fournisseur))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email is not valid");

        verify(mockDao, never()).save(any());

    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void fournisseurShouldBeExistsByEmail() {
    }
}
package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.Fournisseur;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.exception.NotFoundException;
import com.example.visionarycrofting.repository.FournisseurDao;
import com.example.visionarycrofting.services.impl.FournisseurServiceIml;
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
class FournisseurServiceTest {


    @Mock
    private FournisseurDao mockDao;
    private FournisseurService serviceTest;

    @BeforeEach
    void setUp() {
        serviceTest = new FournisseurServiceIml(mockDao);
    }

    @Test
    void canIGetAllFournisseur() {
        serviceTest.findAll();
        verify(mockDao).findAll();
    }

    @Test
    void canNotDeleteFournisseurByEmail() {
        String email = "hamza@gmail.com";

        given(mockDao.existsByEmail(email)).willReturn(false);
        assertThatThrownBy(() -> serviceTest.deleteByEmail("hamza@gmail.com")).isInstanceOf(NotFoundException.class).hasMessageContaining("Bayer with email: " + email + " does not exists");
    }

    @Test
    void canDeleteFournisseurByEmail() {
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
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setName("hamza");
        fournisseur.setEmail("hamza@gmail.com");
        fournisseur.setPassword("AZERTY");
        fournisseur.setPhone("0654656432");

        serviceTest.save(fournisseur);

        ArgumentCaptor<Fournisseur> fournisseurArgumentCaptor = ArgumentCaptor.forClass(Fournisseur.class);
        verify(mockDao).save(fournisseurArgumentCaptor.capture());
        Fournisseur capture = fournisseurArgumentCaptor.getValue();
        assertThat(capture).isEqualTo(fournisseur);
    }

    @Test
    void canNotAddFournissourEmailToken() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setName("hamza");
        fournisseur.setEmail("hamza@gmail.com");
        fournisseur.setPassword("AZERTY");
        fournisseur.setPhone("0654656432");

        given(mockDao.existsByEmail(fournisseur.getEmail())).willReturn(true);
        assertThatThrownBy(() -> serviceTest.save(fournisseur))
                .isInstanceOf(BadRequestException.class).hasMessageContaining("Email " + fournisseur.getEmail() + " is token");
    }

    @Test
    void canNotAddFournissourWithEmailNotValid() {
        Fournisseur fournisseur = new Fournisseur();
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
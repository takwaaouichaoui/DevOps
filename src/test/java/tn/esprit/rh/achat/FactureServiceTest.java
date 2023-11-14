package tn.esprit.rh.achat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@Slf4j


public class FactureServiceTest {;


        @InjectMocks
        FactureServiceImpl factureService;

        @Mock
        FactureRepository factureRepository;

        @Test
        void testAddFacture() {
            // Create a Facture instance and set its properties
            Facture addedFacture = new Facture();
            addedFacture.setMontantRemise(100.0f);
            addedFacture.setMontantFacture(500.0f);
            addedFacture.setDateCreationFacture(new Date());
            addedFacture.setDateDerniereModificationFacture(new Date());
            addedFacture.setArchivee(false);

            // Mock the behavior of your repository to return the addedFacture when save is called
            Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(addedFacture);

            // Call the service method you want to test
            Facture savedFacture = factureService.addFacture(addedFacture);

            // Assertions
            assertNotNull(savedFacture);
            // Add more specific assertions based on the behavior you expect
        }

        @Test
        void testRetrieveFacture() {
            // Create a Facture instance and set its properties
            Facture addedFacture = new Facture();
            addedFacture.setIdFacture(1L);
            addedFacture.setMontantRemise(100.0f);
            addedFacture.setMontantFacture(500.0f);
            addedFacture.setDateCreationFacture(new Date());
            addedFacture.setDateDerniereModificationFacture(new Date());
            addedFacture.setArchivee(false);

            // Mock the behavior of your repository to return a Facture when findById is called
            Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(addedFacture));

            // Call the service method you want to test
            Facture retrievedFacture = factureService.retrieveFacture(1L);

            // Assertions
            assertNotNull(retrievedFacture);
            // Add more specific assertions based on the behavior you expect
        }
    }

